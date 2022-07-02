package com.github.coaixy.AmongUs.Commands

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.subCommand

val stateCommand = subCommand {
    execute<ProxyCommandSender> { sender, context, argument ->
        guiSendState(sender as Player)
    }
}