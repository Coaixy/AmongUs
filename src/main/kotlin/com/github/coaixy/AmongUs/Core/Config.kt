package com.github.coaixy.AmongUs.Core

import org.bukkit.configuration.file.YamlConfiguration
import taboolib.common.io.newFile
import taboolib.common.platform.function.getDataFolder

val configObject: YamlConfiguration
    get() {
        val path: String = getDataFolder().path + "\\config.yml"
        return YamlConfiguration.loadConfiguration(newFile(path, create = false, folder = false))
    }
val langConfigObject: YamlConfiguration
    get() {
        val path: String = getDataFolder().path + "\\lang.yml"
        return YamlConfiguration.loadConfiguration(newFile(path, create = false, folder = false))
    }
val Msg_Join_Fail: String?
get() {
    return langConfigObject.getString("Join-Failed")
}
val Msg_Join_Success: String?
    get() {
        return langConfigObject.getString("Join-Success")
    }
val Msg_Match_State: List<String>
    get() {
        return langConfigObject.getStringList("Match-State")
    }
val Msg_Leave_Success: String?
    get() {
        return langConfigObject.getString("Leave-Success")
    }
val Msg_Leave_Fail: String?
    get() {
        return langConfigObject.getString("Leave-Failed")
    }