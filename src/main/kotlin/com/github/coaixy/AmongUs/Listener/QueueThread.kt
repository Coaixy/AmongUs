package com.github.coaixy.AmongUs.Listener

import com.github.coaixy.AmongUs.Core.Config_Time
import com.github.coaixy.AmongUs.Mechanism.Match
import taboolib.common.platform.function.info

object QueueThread: Thread() {
    @Override
    override fun run(){
        info("队列监听线程已启动")
        val sleepTime = Config_Time
        while (false){
            for (i in Match.getWaitList()){
                Match.start(i)
            }
            sleep(sleepTime)
        }
    }
}