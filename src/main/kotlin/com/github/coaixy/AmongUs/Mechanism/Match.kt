package com.github.coaixy.AmongUs.Mechanism

import taboolib.common.platform.function.getDataFolder
import java.io.File

object Match {

    fun list(): MutableList<Int> {
        val fileName = getDataFolder().path + "\\match\\list.txt"
        val list =  File(fileName).readLines()
        val result = mutableListOf<Int>()
        for (i in list){
            val path = getDataFolder().path + "\\match\\$i.txt"
            if (File(path).readLines()[0] == "1"){
                result.add(i.toInt())
            }
        }
        return result
    }

    fun add(name:String,id:Int){

    }
    fun remove(name:String,id:Int){

    }
    fun listToString(list:List<String>):String{
        val result = ""
        return result;
    }
}