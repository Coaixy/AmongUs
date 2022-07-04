package com.github.coaixy.AmongUs

import com.github.coaixy.AmongUs.Listener.EndThread
import com.github.coaixy.AmongUs.Listener.QueueThread
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.common.platform.function.releaseResourceFile

object AmongUs : Plugin() {

    override fun onEnable() {
        info("成功加载AmongUs")
        releaseResourceFile("config.yml",false)
        releaseResourceFile("lang.yml",false)
        releaseResourceFile("match/list.txt",false)

        QueueThread.start()
        EndThread.start()
    }
}