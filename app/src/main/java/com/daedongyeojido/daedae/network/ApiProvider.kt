package com.daedongyeojido.daedae.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {
    private val BASE_URL = "https://prod-server.xquare.app/jung-ho/"

    private val instance: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    fun getRetrofit(): Retrofit {
        return instance
    }
}