package com.github.coaixy.AmongUs.Commands

import com.github.coaixy.AmongUs.Core.Msg_Join_Fail
import com.github.coaixy.AmongUs.Core.colored
import com.github.coaixy.AmongUs.Mechanism.Match
import org.bukkit.Bukkit
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.subCommand

val joinCommand = subCommand {
    execute<ProxyCommandSender> { sender, context, argument ->
        val list = argument.split(" ")
        // list[0] = "join"
        val roomId = list[1].toInt()
        val name = sender.name
        if (Match.getPlayerState(name) == 0){
            Match.add(name,Match.getAvailableRoom())
            Bukkit.getPlayer(name)?.let { guiSendState(it) }
        }else{
            Msg_Join_Fail?.let { colored(it) }?.let { sender.sendMessage(it) }
        }
    }
}