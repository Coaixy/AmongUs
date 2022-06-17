package com.github.coaixy.AmongUs.Commands

import com.github.coaixy.AmongUs.Core.colored
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
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
    val result = Bukkit.createInventory(null,54, colored("&eAmong Us"))
    val testItem = ItemStack(Material.APPLE)
    result.setItem(1,testItem)
    return result
}
