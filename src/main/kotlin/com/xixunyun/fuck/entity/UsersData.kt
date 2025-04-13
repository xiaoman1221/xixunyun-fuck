package com.xixunyun.fuck.entity

data class UsersData(
    val users: List<User>,
){
    data class User(
        var schoolId: Int,
        val name: String,
        val account: String,
        val model: String,
        val phone: String,
        val password: String,
        val signTime: String,
        val startMonth: String,
        val endMonth: String,
        val workLong: Float,
        val workLat: Float,
        val workLocationName: String,
        val workLocationAddress: String,
        val homeLong: String,
        val homeLat: String,
        val homeLocationName: String,
        val homeLocationAddress: String,
        val mouthSleep: Int,
        val macAddress: String
    )
}
