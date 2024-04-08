package com.daedongyeojido.daedae.feature.club.detail.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.daedongyeojido.daedae.R
import com.daedongyeojido.daedae.databinding.FragmentClubDetailInfoBinding
import com.daedongyeojido.daedae.feature.club.model.ClubDetailInfo
import com.daedongyeojido.daedae.feature.club.model.ClubInfo

class ClubDetailInfoFragment(private val clubInfo: ClubDetailInfo) : Fragment() {
    private lateinit var binding: FragmentClubDetailInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClubDetailInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClubDetailInfo()
    }

    private fun setClubDetailInfo() {
        clubInfo.clubBannerUrl?.let {
            Glide.with(binding.ivClubDetailInfoBanner.context)
                .load(clubInfo.clubBannerUrl)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                .into(binding.ivClubDetailInfoBanner)
        }
        clubInfo.introduction?.let { binding.clubExplain = it }

        if (clubInfo.tags != null) {
            for (tagText in clubInfo.tags) {
                val tag = TextView(activity)
                tag.text = tagText
                tag.setTextAppearance(R.style.text_club_detail_tags)
                binding.layClubDetailInfoTags.addView(tag)
            }
        }
    }
}