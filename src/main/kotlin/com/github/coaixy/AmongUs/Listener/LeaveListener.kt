package com.github.coaixy.AmongUs.Listener

import com.github.coaixy.AmongUs.Mechanism.Match
import org.bukkit.event.player.PlayerQuitEvent
import taboolib.common.platform.event.SubscribeEvent

object LeaveListener {
    @SubscribeEvent
    fun e(e:PlayerQuitEvent){
        if (Match.getPlayerState(e.player.name) > 0){
            Match.delete(e.player.name,Match.getPlayerRoom(e.player.name))

        }
    }
}