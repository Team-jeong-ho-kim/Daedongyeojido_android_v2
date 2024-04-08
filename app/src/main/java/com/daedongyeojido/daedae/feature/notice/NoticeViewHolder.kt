package com.daedongyeojido.daedae.feature.notice

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.daedongyeojido.daedae.R
import com.daedongyeojido.daedae.databinding.ItemNoticeRecyclerBinding
import com.daedongyeojido.daedae.feature.notice.model.Notice

class NoticeViewHolder(private val binding: ItemNoticeRecyclerBinding) : ViewHolder(binding.root) {
    val btn = binding.btnNoticeItemApply
    fun bind(noticeData: Notice) {
        binding.noticeTitle = noticeData.noticeTitle
        binding.clubName = noticeData.clubName
        binding.noticePeriod = noticeData.recruitDay.startDay + " ~ " + noticeData.recruitDay.endDay

        for (a in noticeData.major) {
            val major = TextView(binding.root.context)
            major.text = a
            major.setTextAppearance(R.style.text_notice_major)
            binding.layNoticeItemMajor.addView(major)
        }
    }
}