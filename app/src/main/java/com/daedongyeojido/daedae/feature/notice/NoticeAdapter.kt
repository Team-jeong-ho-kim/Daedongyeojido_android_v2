package com.daedongyeojido.daedae.feature.notice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.daedongyeojido.daedae.databinding.ItemNoticeRecyclerBinding
import com.daedongyeojido.daedae.feature.notice.model.Notice

class NoticeAdapter(private val noticeList: List<Notice>, private val noticeClickListener: NoticeClickListener) : Adapter<NoticeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding = ItemNoticeRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(binding)
    }

    override fun getItemCount(): Int = noticeList.size
    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(noticeList[position])
        holder.btn.setOnClickListener {
            noticeClickListener.onNoticeClick(noticeList[position].id)
        }
//        holder.itemView.setOnClickListener {
//            noticeClickListener.onNoticeClick(noticeList[position].id)
//        }
    }

    interface NoticeClickListener {
        fun onNoticeClick(noticeId: Long)
    }
}