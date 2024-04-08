package com.daedongyeojido.daedae.feature.notice.model

import java.time.LocalDate

data class NoticeDetailResponse(
    val clubName: String,
    val noticeTitle: String,
    val noticeExplain: String,
    val clubExplain: String,
    val fields: List<Field>,
    val recruitDay: RecruitDay,
    val applyMethod: String,
    val interviewDay: InterviewDay,
    val inquiry: String,
    val weWant: String,
    val assignment: String
)

data class Field(
    val major: String,
    val todo: String
)

data class InterviewDay(
    val startDay: String,
    val endDay: String
)