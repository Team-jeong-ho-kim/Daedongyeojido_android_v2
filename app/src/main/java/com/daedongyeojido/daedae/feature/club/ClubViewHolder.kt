package com.daedongyeojido.daedae.feature.club

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.daedongyeojido.daedae.R
import com.daedongyeojido.daedae.databinding.ItemClubRecyclerBinding
import com.daedongyeojido.daedae.feature.club.model.ClubDetailResponse
import com.daedongyeojido.daedae.feature.club.model.ClubInfo

class ClubViewHolder(private val bindnig: ItemClubRecyclerBinding) : ViewHolder(bindnig.root) {
    fun bind(clubData: ClubInfo) {
        bindnig.clubName = clubData.clubName
        bindnig.clubExplain = clubData.title
        Glide.with(bindnig.ivClubItemLogo.context).load(clubData.clubImageUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20))).into(bindnig.ivClubItemLogo)

        for(tagText in clubData.tags) {
            val tag = TextView(bindnig.root.context)
            tag.text = tagText
            tag.setTextAppearance(R.style.text_club_item_tags)
            bindnig.layClubItemTags.addView(tag)
        }
    }
}