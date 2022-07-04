package com.github.coaixy.AmongUs.Commands

import com.github.coaixy.AmongUs.Core.*
import com.github.coaixy.AmongUs.Mechanism.Match
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.subCommand
import taboolib.common.platform.function.getDataFolder
import java.io.File

val guiCommand = subCommand {
    execute<ProxyCommandSender> { sender, context, argument ->
        val player:ProxyCommandSender = sender
        Bukkit.getPlayer(player.name)?.openInventory(inv)
    }
}
val inv:Inventory
get() {
    //21 23 31
    val title = configObject.getString("menu-title")
    val result = Bukkit.createInventory(null,54, colored(title.toString()))

    val itemJoin = ItemStack(Material.IRON_SWORD)
    val itemMeta = itemJoin.itemMeta
    itemMeta?.setDisplayName(colored("&e参与匹配"))
    itemJoin.itemMeta = itemMeta
    result.setItem(21,itemJoin)

    val itemLeave = ItemStack(Material.IRON_DOOR)
    itemMeta?.setDisplayName(colored("&e离开匹配"))
    itemLeave.itemMeta = itemMeta
    result.setItem(23,itemLeave)

    val itemQueue = ItemStack(Material.ARROW)
    itemMeta?.setDisplayName(colored("&e匹配队伍"))
    itemQueue.itemMeta = itemMeta
    result.setItem(31,itemQueue)
    return result
}

fun guiSendState(p: Player){
    for (i in Msg_Match_State){
        if(!i.contains("{{playerList}}")){
            var text = i.replace("{{roomNumber}}",Match.getRoomNumber(Match.getPlayerRoom(p.name)).toString())
            text = text.replace("{{roomId}}",Match.getPlayerRoom(p.name).toString())
            p.sendMessage(colored(text))
        }else{
            val color = i.replace("{{playerList}}","")
            for (j in Match.getPlayerList(Match.getPlayerRoom(p.name))){
                p.sendMessage(colored(color+j))
            }
        }
    }
}
fun guiJoin(p: Player){
    if (Match.getPlayerState(p.name) >= 1){
        for (i in Msg_Join_Fail!!.split("\n")){
            p.sendMessage(colored(i))
        }
    }else{
        val roomId = Match.getAvailableRoom()
        val path = getDataFolder().path+"\\match\\$roomId.txt"
        if (!File(path).exists()){
            val f = File(path)
            f.createNewFile()
            f.writeText("1")
        }
        for (i in Msg_Join_Success!!.replace("{{roomId}}",roomId.toString()).split("\n")){
            p.sendMessage(colored(i))
        }
        Match.add(p.name,roomId)
    }
    guiSendState(p)
}
fun guiLeave(p: Player){
    if (Match.getPlayerState(p.name) < 1){
        for (i in Msg_Leave_Fail!!.split("\n")){
            p.sendMessage(colored(i))
        }
    }else{
        for (i in Msg_Leave_Success!!.split("\n")){
            p.sendMessage(colored(i))
        }
        Match.delete(p.name,Match.getPlayerRoom(p.name))
    }
}
fun guiQueue(p: Player){
    guiSendState(p)
}

