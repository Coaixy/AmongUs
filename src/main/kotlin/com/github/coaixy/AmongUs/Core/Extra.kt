package com.github.coaixy.AmongUs.Core

fun getHelp():MutableList<String>{
        val result = configObject.getStringList("help").toMutableList()
        for ((index,value) in result.withIndex()){
            result[index] = colored(result[index] as String)
        }
        return result
    }
fun colored(text:String):String{
        return text.replace("&","§")
}
