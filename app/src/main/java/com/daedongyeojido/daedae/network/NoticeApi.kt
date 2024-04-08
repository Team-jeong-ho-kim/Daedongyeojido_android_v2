package com.daedongyeojido.daedae.network

import com.daedongyeojido.daedae.feature.notice.model.NoticeDetailResponse
import com.daedongyeojido.daedae.feature.notice.model.NoticeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface NoticeApi {
    @GET("notice/all")
    fun getAllNotice(@Header("Authorization") token: String): Call<NoticeResponse>

    @GET("notice/info/{noticeId}")
    fun getNotice(@Header("Authorization") token: String, @Path("noticeId") noticeId: Long): Call<NoticeDetailResponse>
}