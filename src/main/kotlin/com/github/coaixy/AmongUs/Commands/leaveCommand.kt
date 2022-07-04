package com.github.coaixy.AmongUs.Commands

import com.github.coaixy.AmongUs.Mechanism.Match
import org.bukkit.Bukkit
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.subCommand

val leaveCommand = subCommand {
    execute<ProxyCommandSender> { sender, _, _ ->
        val p = Bukkit.getPlayer(sender.name)
        if (p != null && Match.getPlayerState(p.name) == 1 ) {
            Match.delete(p.name,Match.getPlayerRoom(p.name))
        }
    }
}