package com.github.coaixy.AmongUs.Core

import org.bukkit.configuration.file.YamlConfiguration
import taboolib.common.io.newFile
import taboolib.common.platform.function.getDataFolder





val configObject: YamlConfiguration
    get() {
        val path: String = getDataFolder().path + "\\config.yml"
        return YamlConfiguration.loadConfiguration(newFile(path, create = false, folder = false))
    }
