package com.github.coaixy.AmongUs.Listener

import com.github.coaixy.AmongUs.Core.colored
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import taboolib.common.platform.event.SubscribeEvent


object guiListener {
    fun getItemStack():ItemStack{
        val itemJoin = ItemStack(Material.IRON_SWORD)
        val itemMeta = itemJoin.itemMeta
        itemMeta?.setDisplayName(colored("&e参与匹配"))
        itemJoin.itemMeta = itemMeta
        return itemJoin
    }
    @SubscribeEvent
    fun e(e:InventoryClickEvent){
        e.whoClicked.sendMessage("1")
        if(e.inventory.contains(getItemStack(),21)){
            e.whoClicked.sendMessage("1")
        }
    }
}