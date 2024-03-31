package com.daedongyeojido.daedae.feature.login.model

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessExpiredAt: String,
    val refreshExpiredAt: String,
    val part: String
)
