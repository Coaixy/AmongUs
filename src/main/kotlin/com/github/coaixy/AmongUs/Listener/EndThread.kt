package com.github.coaixy.AmongUs.Listener

import com.github.coaixy.AmongUs.Core.Config_Time
import taboolib.common.platform.function.info

object EndThread: Thread() {
    @Override
    override fun run(){
        info("队列监听线程已启动")
        val sleepTime = Config_Time
        while (false){

            sleep(sleepTime)
        }
    }
}