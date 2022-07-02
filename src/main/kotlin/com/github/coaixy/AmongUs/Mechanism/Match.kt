package com.github.coaixy.AmongUs.Mechanism

import taboolib.common.platform.function.getDataFolder
import java.io.File

object Match {


    //获取可以加人的队列
    // 1 等待中  2 已经开始
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
        return if (result.size<1 && File(fileName).readLines().isNotEmpty()){
            newRoom(list[list.size-1].toInt())
        }else if (result.size < 1 ){
            1
        }else{
            result[0]
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
                    flag = getMethod(i.toInt())
                }
            }
        }
        return flag
    }
    fun getRoomNumber(roomId: Int):Int{
        return File(getDataFolder().path + "\\match\\$roomId.txt").readLines().size-1
    }
    fun getPlayerRoom(playerName: String):Int{
        var result = 1
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

    fun deleteRoom(roomId: Int){
        val fileName = getDataFolder().path + "\\match\\list.txt"
        val list = File(fileName).readLines().toMutableList()
        list.add(roomId.toString())
        var result = ""
        for (i in list){
            if (i != roomId.toString()){
                result+=i+"\n"
            }
        }
        File(fileName).writeText(result)
    }

    fun addRoom(roomId: Int){
        val fileName = getDataFolder().path + "\\match\\list.txt"
        val list = File(fileName).readLines().toMutableList()
        list.add(roomId.toString())
        var result = ""
        for (i in list){
            result+=i+"\n"
        }
        File(fileName).writeText(result)
    }
    fun add(playerName:String,roomId:Int):Boolean{
        var text = getText(roomId)
        return if (text[0] == '1'){
            text+=playerName
            File(getDataFolder().path + "\\match\\$roomId.txt").writeText(text)
            true
        }else{
            false
        }
    }
    fun delete(playerName:String,roomId:Int):Boolean{
        val text = getText(roomId)
        if (getPlayerState(playerName) == 2)return false
        if (getPlayerState(playerName) == 1){
            var result = ""
            for (i in text.split("\n")){
                if (i!=playerName){
                    result+=i+"\n"
                }
            }
            File(getDataFolder().path + "\\match\\$roomId.txt").writeText(result)
            return true
        }else{
            return false
        }
    }

    private fun getMethod(roomId: Int):Int{
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
        val num = lastNum+1
        if (!File(getDataFolder().path + "\\match\\$num.txt").exists()){
            File(getDataFolder().path + "\\match\\$num.txt").createNewFile()
        }else{
            File(getDataFolder().path + "\\match\\$num.txt").writeText("1")
        }
        addRoom(lastNum+1)
        return lastNum+1
    }
}