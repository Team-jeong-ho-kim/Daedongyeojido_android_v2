package com.daedongyeojido.daedae.feature.club.detail.member

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.daedongyeojido.daedae.databinding.ItemClubDetailMemberRecyclerBinding
import com.daedongyeojido.daedae.feature.club.model.ClubMember

class ClubMemberViewHolder(private val binding: ItemClubDetailMemberRecyclerBinding) : ViewHolder(binding.root) {
    fun bind(memberData: ClubMember) {
        Glide.with(binding.ivClubDetailMemberProfile.context).load(memberData.profileImageUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20))).into(binding.ivClubDetailMemberProfile)
        binding.memberName = memberData.name
        binding.major = memberData.major
        binding.introduce = memberData.oneLiner
    }
}