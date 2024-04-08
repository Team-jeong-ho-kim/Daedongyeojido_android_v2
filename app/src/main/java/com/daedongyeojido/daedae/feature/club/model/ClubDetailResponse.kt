package com.daedongyeojido.daedae.feature.club.model

data class ClubDetailResponse(
    val clubName: String,
    val title: String,
    val introduction: String,
    val clubImageUrl: String,
    val clubBannerUrl: String,
    val tags: List<String>,
    val clubMembers: List<ClubMember>,
    val questResponses: List<Question>
)

data class ClubMember(
    val name: String?,
    val major: String?,
    val oneLiner: String?,
    val profileImageUrl: String?
)

data class Question(
    val questionId: Int,
    val question: String,
    val answer: String
)

data class ClubDetailInfo(
    val clubName: String,
    val title: String?,
    val introduction: String?,
    val clubBannerUrl: String?,
    val tags: List<String>?
)