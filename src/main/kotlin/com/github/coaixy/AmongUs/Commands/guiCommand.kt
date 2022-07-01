package com.github.coaixy.AmongUs.Commands

import com.github.coaixy.AmongUs.Core.*
import com.github.coaixy.AmongUs.Mechanism.Match
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.subCommand

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
        val roomId = Match.getPlayerRoom(p.name)
        if (i.contains("{{playerList}}")){
            i.replace("{{playerList}}","")
            p.sendMessage(colored(i))
            for (j in Match.getPlayerList(roomId)){
                p.sendMessage(j)
            }
        }else{
            i.replace("{{roomId}}",roomId.toString())
            i.replace("{{roomNumber}}",Match.getRoomNumber(roomId).toString())
            p.sendMessage(colored(i))
        }
    }
}
fun guiJoin(p:Player){
    if (Match.getPlayerState(p.name) >= 1){
        for (i in Msg_Join_Fail!!.split("\n")){
            p.sendMessage(colored(i))
        }
    }else{
        val roomId = Match.getAvailableList()
        for (i in Msg_Join_Success!!.replace("{{roomId}}",roomId.toString()).split("\n")){
            p.sendMessage(colored(i))
        }
        Match.add(p.name,roomId)
    }
    guiSendState(p)
}
fun guiLeave(p:Player){
    if (Match.getPlayerState(p.name) < 1){
        for (i in Msg_Leave_Fail!!.split("\n")){
            p.sendMessage(colored(i))
        }
    }else{
        for (i in Msg_Leave_Success!!.split("\n")){
            p.sendMessage(colored(i))
        }
        Match.delete(p.name,Match.getPlayerRoom(p.name))
        guiSendState(p)
    }
}
fun guiQueue(p:Player){
    guiSendState(p)
}

