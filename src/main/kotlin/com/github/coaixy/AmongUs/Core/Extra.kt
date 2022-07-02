package com.github.coaixy.AmongUs.Core

import com.github.coaixy.AmongUs.Mechanism.Match
import org.bukkit.entity.Player
import taboolib.common.platform.ProxyCommandSender

fun getHelp(): List<String> {
    return langConfigObject.getStringList("help")
}
fun colored(text:String):String{
        return text.replace("&","§")
}

fun guiHook(text:String, player:Player):String{
    var result = ""
    result = result.replace("{{roomId}}", Match.getPlayerRoom(player.name).toString())
    result = result.replace("{{roomNumber}}", Match.getRoomNumber(Match.getPlayerRoom(player.name)).toString())
    return result
}