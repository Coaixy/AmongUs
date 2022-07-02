package com.github.coaixy.AmongUs

import taboolib.common.platform.Plugin
import taboolib.common.platform.function.getDataFolder
import taboolib.common.platform.function.info
import taboolib.common.platform.function.releaseResourceFile
import java.io.File

object AmongUs : Plugin() {

    override fun onEnable() {
        info("成功加载AmongUs")
        releaseResourceFile("config.yml",false)
        releaseResourceFile("lang.yml",false)
        releaseResourceFile("match//list.txt",false)

    }
}