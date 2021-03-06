package com.github.coaixy.AmongUs.Commands

import com.github.coaixy.AmongUs.Core.getHelp
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.subCommand

val helpCommand = subCommand {
    execute<ProxyCommandSender> { sender, context, argument ->
        for (text in getHelp()){
            sender.sendMessage(text)
        }
    }
}