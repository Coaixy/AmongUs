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