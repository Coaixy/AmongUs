package com.github.coaixy.AmongUs.Listener

import com.github.coaixy.AmongUs.Mechanism.Match
import org.bukkit.event.player.AsyncPlayerChatEvent
import taboolib.common.platform.event.SubscribeEvent

object CmdListener {
    @SubscribeEvent
    //拦截指令
    fun e(e:AsyncPlayerChatEvent){
        if (Match.getPlayerState(e.player.name) == 2){
            if(!(e.message.contains("/") && match(e.message))){
                e.isCancelled=true
            }
        }
    }
    fun match(text:String):Boolean{
        return text.get(1).toString()=="a" && text.get(1).toString()=="u" && text.get(1).toString()=="s"
    }
}