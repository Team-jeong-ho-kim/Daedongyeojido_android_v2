package com.daedongyeojido.daedae.network

import com.daedongyeojido.daedae.feature.login.model.LoginRequest
import com.daedongyeojido.daedae.feature.login.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}