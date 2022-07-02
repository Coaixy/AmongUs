package com.github.coaixy.AmongUs.Listener

import com.github.coaixy.AmongUs.Commands.guiJoin
import com.github.coaixy.AmongUs.Commands.guiLeave
import com.github.coaixy.AmongUs.Commands.guiQueue
import com.github.coaixy.AmongUs.Core.colored
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import taboolib.common.platform.event.SubscribeEvent


object guiListener {
    @SubscribeEvent
    fun e(e:InventoryClickEvent){
        val inv = e.inventory
        val title = inv.getItem(21)?.itemMeta?.displayName
        if (title.equals(colored("&e参与匹配"))){
            //判断是否为AmongUs的GUI
            val p = e.whoClicked
            if (e.rawSlot==21) guiJoin(p as Player)
            if (e.rawSlot==23) guiLeave(p as Player)
            if (e.rawSlot==31) guiQueue(p as Player)
            e.isCancelled
        }
    }
}