package com.daedongyeojido.daedae.feature.club.detail.qna

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.daedongyeojido.daedae.databinding.ItemClubDetailQnaRecyclerBinding
import com.daedongyeojido.daedae.feature.club.model.Question

class ClubQnaAdapter(private val qnaList: List<Question>) : Adapter<ClubQnaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubQnaViewHolder {
        val binding = ItemClubDetailQnaRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClubQnaViewHolder(binding)
    }

    override fun getItemCount(): Int = qnaList.size
    override fun onBindViewHolder(holder: ClubQnaViewHolder, position: Int) {
        holder.onBind(qnaList[position])
    }
}