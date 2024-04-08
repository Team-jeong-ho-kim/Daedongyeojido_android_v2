package com.daedongyeojido.daedae.feature.club.detail.qna

import android.view.View
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.daedongyeojido.daedae.databinding.ItemClubDetailQnaRecyclerBinding
import com.daedongyeojido.daedae.feature.club.model.Question

class ClubQnaViewHolder(private val binding: ItemClubDetailQnaRecyclerBinding) : ViewHolder(binding.root) {
    fun onBind(questionData: Question) {
        binding.question = questionData.question
        binding.answer = questionData.answer
        binding.ivClubDetailQnaArrow.setOnClickListener {
            if (binding.tvClubDetailQnaAnswer.isVisible) {
                binding.tvClubDetailQnaAnswer.visibility = View.GONE
                binding.ivClubDetailQnaArrow.animate().apply {
                    duration = 200
                    rotation(0f)
                }
            } else {
                binding.tvClubDetailQnaAnswer.visibility = View.VISIBLE
                binding.ivClubDetailQnaArrow.animate().apply {
                    duration = 200
                    rotation(90f)
                }
            }
        }
    }
}