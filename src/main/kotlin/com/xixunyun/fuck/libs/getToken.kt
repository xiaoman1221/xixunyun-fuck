/*
 *
 *  * Copyright © 2025 小满1221
 *  * Author: 小满1221
 *  * Date: 2025/3/30
 *
 */

package com.xixunyun.fuck.libs

import cn.rtast.rutil.http.Http
import com.xixunyun.fuck.Config
import com.xixunyun.fuck.entity.UserToken
import com.xixunyun.fuck.entity.UsersData

fun getToken(user: UsersData.User,config: Config): UserToken? {
        val url = "https://api.xixunyun.com/login/api?from=${config.from}&version=${config.version}&platform=${config.platform}&entrance_year=0&graduate_year=0&school_id=${user.schoolId}"
        val headers = mapOf(
            "Content-Type" to "application/x-www-form-urlencoded",
            "Host" to "api.xixunyun.com",
            "Accept-Encoding" to "gzip",
            "User-Agent" to "okhttp/3.8.1"
        )
        val data = mapOf(
            "app_version" to config.version,
            "registration_id" to "",
            "uuid" to "",
            "request_source" to "3",
            "platform" to "2",
            "mac" to user.macAddress,
            "password" to user.password,
            "school_id" to user.schoolId,
            "model" to user.model,
            "app_id" to "cn.vanber.xixunyun.saas",
            "account" to user.account,
            "key" to "",


            )
        val repo = Http.post<UserToken>(url,data,headers)
        println("发送请求")
        return if (repo.code == 20000){
            repo
        }else{
            null
        }
}