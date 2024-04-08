package com.daedongyeojido.daedae.feature.club

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.daedongyeojido.daedae.databinding.ItemClubRecyclerBinding
import com.daedongyeojido.daedae.feature.club.model.ClubInfo

class ClubAdapter(private val clubList: List<ClubInfo>, private val clubClickListener: ClubClickListener) : Adapter<ClubViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        val binding = ItemClubRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClubViewHolder(binding)
    }

    override fun getItemCount(): Int = clubList.size
    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bind(clubList[position])
        holder.itemView.setOnClickListener {
            clubClickListener.onClubClicked(clubList[position].clubName)
        }
    }

    interface ClubClickListener {
        fun onClubClicked(clubName: String)
    }
}