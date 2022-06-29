package com.github.coaixy.AmongUs.Mechanism

import taboolib.common.platform.function.getDataFolder
import java.io.File

object Match {


    //获取可以加人的队列
    fun getAvailableList():Int {
        val fileName = getDataFolder().path + "\\match\\list.txt"
        val list =  File(fileName).readLines()
        val result = mutableListOf<Int>()
        for (i in list){
            val path = getDataFolder().path + "\\match\\$i.txt"
            if (File(path).readLines()[0] == "1"){
                result.add(i.toInt())
            }
        }
        //如果所有队列已满，则创建新队列
        if (result.size<1){
            return newRoom(list[list.size-1].toInt())
        }else{
            return result[0]
        }
    }
    private fun newRoom(lastNum:Int):Int{
        return lastNum+1
    }

    fun setMeted(roomId:Int,state:Int){
        val fileName = getDataFolder().path + "\\match\\$roomId.txt"
        var text = ""
        for (i in File(fileName).readLines()){
            if (i!="1" && i!="0"){ //不读取校验码
                text = text+i+"\n"
            }
        }
        text = state.toString()+"\n"+text //添加修改后的校验码
        File(fileName).writeText(text)
    }
    fun getPlayerList(roomId:Int):MutableList<String>{
        val result = mutableListOf<String>()
        val fileName = getDataFolder().path + "\\match\\$roomId.txt"
        for (i in File(fileName).readLines()){
            if (i!="1" && i!="0"){ //第一行为房间状态校验码
                result.add(i)
            }
        }
        return result
    }

}