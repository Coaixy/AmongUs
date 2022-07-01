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
    //0 空闲 1 已加入队列 2 已进入游戏
    fun getPlayerState(playerName: String):Int{
        val fileName = getDataFolder().path + "\\match\\list.txt"
        val list =  File(fileName).readLines()
        var flag = 0
        for (i in list){
            for (j in getPlayerList(i.toInt())){
                if (j == playerName){
                    if (getMethod(i.toInt()) == 1)flag = 2
                    if (getMethod(i.toInt()) == 0)flag = 1
                }
            }
        }
        return flag
    }
    fun getRoomNumber(roomId: Int):Int{
        return File(getDataFolder().path + "\\match\\$roomId.txt").readLines().size-1
    }
    fun getPlayerRoom(playerName: String):Int{
        var result = 0
        val fileName = getDataFolder().path + "\\match\\list.txt"
        val list =  File(fileName).readLines()
        for (i in list){
            for (j in getPlayerList(i.toInt())){
                if (j == playerName){
                    result = i.toInt()
                    break
                }
            }
        }
        return result
    }
    fun add(playerName:String,roomId:Int):Boolean{
        var text = getText(roomId)
        return if (text[0] == '0'){
            text+=playerName
            File(getDataFolder().path + "\\match\\$roomId.txt").writeText(text)
            true
        }else{
            false
        }
    }
    fun delete(playerName:String,roomId:Int):Boolean{
        val text = getText(roomId)
        var flag = 0
        for (i in text.split("\n")){
            if (i == playerName)flag=1
        }
        return if (flag !=1 || text[0]=='1'){
            false
        }else{
            var result = ""
            for (i in text.split("\n")){
                if (i!=playerName){
                    result+=i+"\n"
                }
            }
            File(getDataFolder().path + "\\match\\$roomId.txt").writeText(result)
            true
        }
    }

    fun getMethod(roomId: Int):Int{
        val text = File(getDataFolder().path+"\\match\\$roomId.txt").readText()
        return text[0].toString().toInt()
    }
    fun setMethod(roomId:Int,state:Int){
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

    private fun getText(roomId:Int):String{
        val fileName = getDataFolder().path + "\\match\\$roomId.txt"
        var result = ""
        for (i in File(fileName).readLines()){
            result+=i+"\n"
        }
        return result
    }
    private fun newRoom(lastNum:Int):Int{
        return lastNum+1
    }
}