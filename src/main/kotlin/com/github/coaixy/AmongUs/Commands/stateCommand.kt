package com.github.coaixy.AmongUs.Commands

import org.bukkit.Bukkit
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.subCommand

val stateCommand = subCommand {
    execute<ProxyCommandSender> { sender, _, _ ->
        val p = Bukkit.getPlayer(sender.name)
        if (p != null) {
            guiSendState(p)
        }
    }
}