package com.daedongyeojido.daedae.network

import com.daedongyeojido.daedae.feature.mypage.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {
    @GET("user/my-info")
    fun getUserInfo(@Header("Authorization") token: String): Call<UserResponse>
}