package com.github.coaixy.AmongUs.Commands

import com.github.coaixy.AmongUs.Core.colored
import com.github.coaixy.AmongUs.Core.configObject
import org.bukkit.Bukkit
import org.bukkit.Material
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

fun guiJoin(){

}
fun guiLeave(){

}
fun guiQueue(){

}

