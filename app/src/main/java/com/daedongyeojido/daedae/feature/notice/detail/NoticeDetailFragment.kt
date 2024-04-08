package com.daedongyeojido.daedae.feature.notice.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.daedongyeojido.daedae.MainActivity
import com.daedongyeojido.daedae.R
import com.daedongyeojido.daedae.databinding.FragmentNoticeDetailBinding
import com.daedongyeojido.daedae.feature.mypage.model.Report
import com.daedongyeojido.daedae.feature.notice.model.NoticeDetailResponse
import com.daedongyeojido.daedae.feature.report.apply.ReportApplyFragment
import com.daedongyeojido.daedae.network.ApiProvider
import com.daedongyeojido.daedae.network.NoticeApi
import com.daedongyeojido.daedae.util.accessToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticeDetailFragment(private val noticeId: Long) : Fragment() {
    private lateinit var binding: FragmentNoticeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoticeDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNoticeInfo()

        binding.ivNoticeDetailBack.setOnClickListener {
            backStack()
        }
        binding.btnNoticeDetailApply.setOnClickListener {
            val mainActivity = activity as MainActivity
            mainActivity.replaceReportFragment(noticeId)
        }
    }

    private fun getNoticeInfo() {
        val apiProvider = ApiProvider.getRetrofit().create(NoticeApi::class.java)
        Log.d("token", accessToken)
        apiProvider.getNotice(accessToken, noticeId).enqueue(object : Callback<NoticeDetailResponse> {
            override fun onResponse(call: Call<NoticeDetailResponse>, response: Response<NoticeDetailResponse>) {
                Log.d("server", response.code().toString())
                if (response.isSuccessful) {
                    response.body()?.let { setNoticeInfo(it) }
                } else {
                    Log.d("server", response.message().toString())
                }
            }
            override fun onFailure(call: Call<NoticeDetailResponse>, t: Throwable) {
                Log.d("server", t.message.toString())
                Toast.makeText(activity, "서버 연동 실패", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setNoticeInfo(response: NoticeDetailResponse) {
        binding.noticeTitle = response.clubName
        binding.noticeExplain = response.clubExplain
        binding.recruitPeriod = response.recruitDay.startDay + " ~ " + response.recruitDay.endDay
        binding.recruitHowto = response.applyMethod
        binding.weWant = response.weWant
        binding.inquiry = response.inquiry
        binding.work = response.assignment

        for(a in response.fields) {
            val tvMajor = TextView(activity)
            tvMajor.text = a.major
            tvMajor.setTextAppearance(R.style.text_notice_detail_major)

            val tvTodo = TextView(activity)
            tvTodo.text = a.todo
            tvTodo.setTextAppearance(R.style.text_notice_detail_work)

            binding.layNoticeDetailMajor.addView(tvMajor)
            binding.layNoticeDetailMajor.addView(tvTodo)
        }
    }

    private fun backStack() {
        val mainActivity = activity as MainActivity
        mainActivity.backFragment()
    }
}