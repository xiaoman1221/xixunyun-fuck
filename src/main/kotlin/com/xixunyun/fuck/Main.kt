package com.xixunyun.fuck

import cn.rtast.rutil.string.fromJson
import com.xixunyun.fuck.entity.UserToken
import com.xixunyun.fuck.entity.UsersData
import com.xixunyun.fuck.libs.getToken
import java.io.File

fun main() {
    val configFile = File("config.json")
    val usersFile = File("./data/users.json")
    val config = configFile.readText().fromJson<Config>()
    val users = usersFile.readText().fromJson<UsersData>()
    users.users.forEach {
        val data = getToken(it, config)
        if (data != null) {
            println(data)
            userSign(data)
            signPush()
        }
    }
}

fun signPush() {
    TODO("Not yet implemented")
}

fun userSign(token: UserToken) {
    val tokenStr = token.toString()
    return
}

