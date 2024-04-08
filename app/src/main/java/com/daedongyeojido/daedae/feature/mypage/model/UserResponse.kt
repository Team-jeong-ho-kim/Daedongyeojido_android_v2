package com.daedongyeojido.daedae.feature.mypage.model

import java.time.LocalDate
import java.time.LocalDateTime

data class UserResponse(
    val classNumber: String,
    val name: String,
    val oneliner: String,
    val githubLink: String,
    val part: String,
    val myClub: String,
    val profileImageUrl: String,
    val major: String,
    val myReport: List<Report>
)

data class Report(
    val id: Int,
    val clubName: String,
    val hopeMajor: String,
    val deadline: LocalDate,
    val reportPassingResult: String,
    val interviewPassingResult: String,
    val interviewStartTime: String,
    val interviewEndTime: String
)
