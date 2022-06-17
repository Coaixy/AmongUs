package com.github.coaixy.AmongUs.Commands

import com.github.coaixy.AmongUs.Core.getHelp
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.subCommand
import taboolib.common.platform.function.info

val helpCommand = subCommand {
    execute<ProxyCommandSender> { sender, context, argument ->
        for (text in getHelp()){
            info(text)
        }
    }
}