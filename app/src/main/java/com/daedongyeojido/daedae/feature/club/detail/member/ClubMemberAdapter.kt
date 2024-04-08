package com.daedongyeojido.daedae.feature.club.detail.member

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.daedongyeojido.daedae.databinding.ItemClubDetailMemberRecyclerBinding
import com.daedongyeojido.daedae.feature.club.model.ClubMember

class ClubMemberAdapter(private val memberList: List<ClubMember>) : Adapter<ClubMemberViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubMemberViewHolder {
        val binding = ItemClubDetailMemberRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClubMemberViewHolder(binding)
    }

    override fun getItemCount(): Int = memberList.size
    override fun onBindViewHolder(holder: ClubMemberViewHolder, position: Int) {
        holder.bind(memberList[position])
    }
}