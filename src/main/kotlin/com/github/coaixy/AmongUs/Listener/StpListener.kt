package com.github.coaixy.AmongUs.Listener

import com.github.coaixy.AmongUs.Mechanism.Match
import org.bukkit.event.player.AsyncPlayerChatEvent
import taboolib.common.platform.event.SubscribeEvent

object StpListener {
    @SubscribeEvent
    //拦截STP指令
    fun e(e:AsyncPlayerChatEvent){
        if (Match.getPlayerState(e.player.name) == 2 && e.message.contains("/stp")){
            e.isCancelled = true
        }
    }

}