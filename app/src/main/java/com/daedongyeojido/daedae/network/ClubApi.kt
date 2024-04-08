package com.daedongyeojido.daedae.network

import com.daedongyeojido.daedae.feature.club.model.ClubDetailResponse
import com.daedongyeojido.daedae.feature.club.model.ClubQuestRequest
import com.daedongyeojido.daedae.feature.club.model.ClubResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ClubApi {
    @GET("main")
    fun getClubList(): Call<ClubResponse>

    @GET("club/info/{clubName}")
    fun getDetailClubInfo(@Path("clubName") clubName: String): Call<ClubDetailResponse>

    @POST("question/quest")
    fun clubQuest(@Header("Authorization") token: String, @Body request: ClubQuestRequest): Call<Void>
}