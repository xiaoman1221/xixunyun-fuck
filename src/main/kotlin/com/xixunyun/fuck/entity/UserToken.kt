package com.xixunyun.fuck.entity

import com.google.gson.annotations.SerializedName

data class UserToken (
    val code: Int,
    val message: String,
    @SerializedName("run_execute_time")
    val runExecuteTime: String,
    val data: Data
){
    data class Data(
        @SerializedName("user_id")
        val userId: Int,
        @SerializedName("school_id")
        val schoolId: Int,
        @SerializedName("user_name")
        val userName: String,
        @SerializedName("user_number")
        val userNumber: String,
        @SerializedName("bind_phone")
        val bindPhone: String,
        @SerializedName("class_name")
        val className: String,
        @SerializedName("entrance_year")
        val entranceYear: String,
        @SerializedName("graduation_year")
        val graduationYear: String,
        val token:String,
        @SerializedName("user_email")
        val userEmail:String,
    )
}