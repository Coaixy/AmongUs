package com.github.coaixy.AmongUs.Core

import com.github.coaixy.AmongUs.Commands.*
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
    @CommandBody(
        optional = true,
        permissionDefault = PermissionDefault.TRUE,
        permission = "AmongUs.user.gui"
    )
    val gui = guiCommand
    @CommandBody(
        optional = true,
        permissionDefault = PermissionDefault.TRUE,
        permission = "AmongUs.user.state"
    )
    val state = stateCommand
    @CommandBody(
        optional = true,
        permissionDefault = PermissionDefault.TRUE,
        permission = "AmongUs.user.join"
    )
    val join = joinCommand
    @CommandBody(
        optional = true,
        permissionDefault = PermissionDefault.TRUE,
        permission = "AmongUs.user.leave"
    )
    val leave = leaveCommand
}