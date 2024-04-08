package com.daedongyeojido.daedae.feature.club.model

data class ClubResponse(
    val banners: List<Banner>,
    val allClubResponses: List<ClubInfo>
)

data class Banner(
    val id: Long,
    val bannerImgUrl: String
)

data class ClubInfo(
    val clubName: String,
    val title: String,
    val clubImageUrl: String,
    val majors: List<Major>,
    val tags: List<String>
)

data class Major(
    val major: String
)