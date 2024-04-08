package com.daedongyeojido.daedae.feature.club.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.daedongyeojido.daedae.MainActivity
import com.daedongyeojido.daedae.R
import com.daedongyeojido.daedae.databinding.FragmentClubDetailBinding
import com.daedongyeojido.daedae.feature.club.model.ClubDetailInfo
import com.daedongyeojido.daedae.feature.club.model.ClubDetailResponse
import com.daedongyeojido.daedae.feature.club.model.ClubMember
import com.daedongyeojido.daedae.feature.club.model.Question
import com.daedongyeojido.daedae.network.ApiProvider
import com.daedongyeojido.daedae.network.ClubApi
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Member

class ClubDetailFragment(private val clubName: String) : Fragment() {
    private lateinit var binding: FragmentClubDetailBinding

    private var clubInfo: ClubDetailInfo = ClubDetailInfo("", "", "", "", listOf())
    private val clubMember = mutableListOf<ClubMember>()
    private val qnaList = mutableListOf<Question>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClubDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getClubDetailInfo()
        val mainActivity = activity as MainActivity

        binding.layClubDetailTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tab.position) {
                    0 -> mainActivity.clubDetailInfo(0, clubInfo, clubMember, qnaList)
                    1 -> mainActivity.clubDetailInfo(1, clubInfo, clubMember, qnaList)
                    2 -> mainActivity.clubDetailInfo(2, clubInfo, clubMember, qnaList)
                }
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun getClubDetailInfo() {
        val apiProvider = ApiProvider.getRetrofit().create(ClubApi::class.java)
        apiProvider.getDetailClubInfo(clubName).enqueue(object : Callback<ClubDetailResponse> {
            override fun onResponse(call: Call<ClubDetailResponse>, response: Response<ClubDetailResponse>) {
                Log.d("server", response.code().toString())
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setClubInfo(responseBody)
                        setClubMember(responseBody.clubMembers)
                        setQuestion(responseBody.questResponses)
                    }
                } else {
                    Log.d("server", response.message())
                }
            }
            override fun onFailure(call: Call<ClubDetailResponse>, t: Throwable) {
                Log.d("server", t.message.toString())
                Toast.makeText(activity, "서버 통신 실패", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun setClubInfo(response: ClubDetailResponse) {
        binding.clubName = response.clubName
        Glide.with(binding.ivClubDetailLogo.context).load(response.clubImageUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20))).into(binding.ivClubDetailLogo)

        clubInfo = ClubDetailInfo(response.clubName, response.title, response.introduction, response.clubBannerUrl, response.tags)
    }
    private fun setClubMember(response: List<ClubMember>) {
        for(member in response) {
            clubMember.add(member)
        }
    }
    private fun setQuestion(response: List<Question>) {
        for (question in response) {
            qnaList.add(question)
        }
    }
}