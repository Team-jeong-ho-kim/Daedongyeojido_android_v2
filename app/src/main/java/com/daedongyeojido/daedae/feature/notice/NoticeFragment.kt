package com.daedongyeojido.daedae.feature.notice

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.daedongyeojido.daedae.MainActivity
import com.daedongyeojido.daedae.databinding.FragmentNoticeBinding
import com.daedongyeojido.daedae.feature.notice.model.Notice
import com.daedongyeojido.daedae.feature.notice.model.NoticeResponse
import com.daedongyeojido.daedae.network.ApiProvider
import com.daedongyeojido.daedae.network.NoticeApi
import com.daedongyeojido.daedae.util.accessToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticeFragment : Fragment(), NoticeAdapter.NoticeClickListener {
    private lateinit var binding: FragmentNoticeBinding
    private val noticeList = mutableListOf<Notice>()
    private val adapter = NoticeAdapter(noticeList, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoticeBinding.inflate(layoutInflater, container, false)
        noticeList.clear()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerNotice.adapter = adapter
        binding.recyclerNotice.layoutManager = LinearLayoutManager(activity)

        getNoticeList()
    }

    private fun getNoticeList() {
        Log.d("token", accessToken)
        val apiProvider = ApiProvider.getRetrofit().create(NoticeApi::class.java)
        apiProvider.getAllNotice(accessToken).enqueue(object : Callback<NoticeResponse> {
            override fun onResponse(call: Call<NoticeResponse>, response: Response<NoticeResponse>) {
                Log.d("server", response.code().toString())
                if (response.isSuccessful) {
                    response.body()?.let { setNoticeList(it) }
                    Log.d("response", response.body().toString())
                } else {
                    Log.d("server", response.message().toString())
                }
            }

            override fun onFailure(call: Call<NoticeResponse>, t: Throwable) {
                Log.d("server", t.message.toString())
                Toast.makeText(activity, "서버 연동 실패", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setNoticeList(response: NoticeResponse) {
        for(notice in response.notices) {
            noticeList.add(notice)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onNoticeClick(noticeId: Long) {
        (activity as MainActivity).noticeDetail(noticeId)
    }
}