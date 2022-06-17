package com.github.coaixy.AmongUs.Core

import com.github.coaixy.AmongUs.Commands.helpCommand
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.PermissionDefault


@CommandHeader(
    name = "aus",
    aliases = ["AmongUs"],
    permission = "AmongUs.use"
)
object Command {
    @CommandBody(
        optional = true,
        permissionDefault = PermissionDefault.TRUE,
        permission = "AmongUs.user.help"
    )
    val help = helpCommand
}