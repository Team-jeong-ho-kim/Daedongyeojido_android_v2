package com.daedongyeojido.daedae.feature.notice.model

data class NoticeResponse(
    val isCreateNotice: Boolean,
    val notices: List<Notice>
)

data class Notice(
    val id: Long,
    val clubName: String,
    val noticeTitle: String,
    val major: List<String>,
    val recruitDay: RecruitDay,
    val isApply: Boolean
)

data class RecruitDay(
    val startDay: String,
    val endDay: String
)