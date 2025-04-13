/*
 *
 *  * Copyright © 2025 小满1221
 *  * Author: 小满1221
 *  * Date: 2025/3/30
 *
 */

package com.xixunyun.fuck

import cn.rtast.rutil.string.fromJson
import com.xixunyun.fuck.entity.UserToken
import com.xixunyun.fuck.entity.UsersData
import com.xixunyun.fuck.libs.EncryptLocation
import com.xixunyun.fuck.libs.getToken
import java.io.File

class GetResources{
    fun getResourcesByFileName(filename: String): ByteArray?{
        val file = this::class.java.classLoader.getResourceAsStream(filename)
        return file!!.readBytes()
    }
}

// 主程序
fun main() {
    val configFile = File("config.json")
    val usersFile = File("./data/users.json")
    val config = configFile.readText().fromJson<Config>()
    val users = usersFile.readText().fromJson<UsersData>()
    users.users.forEach {
        val data = getToken(it, config)
        if (data != null) {
            println(data)
            if(userSign(data,it)){
                signPush(true)
            }else{
                signPush(false)
            }

        }
    }
}
// 通知
fun signPush(info: Boolean) {
    if (info){
        println("OK")
    }else{
        println("WARNING")
    }
}
// 签到主程序
fun userSign(token: UserToken,user: UsersData.User): Boolean {
    if(true){
        println("OK")
        val a = EncryptLocation().encodeLocation(user.workLat.toString(),user.workLong.toString())
        println(a)
    }else{
        println("WARNING")
    }
    return false
}
