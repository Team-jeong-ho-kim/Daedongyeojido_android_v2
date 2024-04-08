package com.daedongyeojido.daedae.feature.mypage

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.daedongyeojido.daedae.MainActivity
import com.daedongyeojido.daedae.databinding.FragmentMypageBinding
import com.daedongyeojido.daedae.feature.login.LoginActivity
import com.daedongyeojido.daedae.feature.mypage.model.Report
import com.daedongyeojido.daedae.feature.mypage.model.UserResponse
import com.daedongyeojido.daedae.feature.report.history.ReportHistoryFragment
import com.daedongyeojido.daedae.network.ApiProvider
import com.daedongyeojido.daedae.network.UserApi
import com.daedongyeojido.daedae.util.accessToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageFragment : Fragment() {
    private lateinit var binding: FragmentMypageBinding
    private val myReports = mutableListOf<Report>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMypageBinding.inflate(layoutInflater, container, false)

        getUserData()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layMypageHistory.setOnClickListener {
            val mainActivity = activity as MainActivity
            mainActivity.replaceReportHistoryFragment(myReports)
        }
        binding.layMypageBug.setOnClickListener {
            AlertDialog.Builder(activity)
                .setTitle("개발 중인 기능입니다")
                .setPositiveButton("확인", DialogInterface.OnClickListener { _, _ -> })
                .show()
        }
        binding.btnMypageLogout.setOnClickListener {
            AlertDialog.Builder(activity)
                .setTitle("로그아웃하시겠습니까?")
                .setPositiveButton("예", DialogInterface.OnClickListener { _, _ ->
                    val intent = Intent(activity, LoginActivity::class.java)
                    accessToken = ""
                    startActivity(intent)
                })
                .setNegativeButton("아니요", DialogInterface.OnClickListener { _, _ -> })
                .show()
        }
    }

    private fun getUserData() {
        val apiProvider = ApiProvider.getRetrofit().create(UserApi::class.java)
        Log.d("token", accessToken)
        apiProvider.getUserInfo(accessToken).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                Log.d("server", response.code().toString())
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setUserData(responseBody)
                        setMyReports(responseBody.myReport)
                    }
                } else {
                    Log.d("server", response.message().toString())
                    Toast.makeText(activity, "사용자 정보를 찾을 수 없습니다", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("server", t.message.toString())
                Toast.makeText(activity, "서버 연동 실패", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setUserData(response: UserResponse) {
        binding.userName = response.name
        binding.userClub = response.myClub
        binding.userNumber = response.classNumber
        Glide.with(binding.ivMypageProfile.context).load(response.profileImageUrl)
            .circleCrop().into(binding.ivMypageProfile)
    }

    private fun setMyReports(response: List<Report>) {
        myReports.clear()
        for (report in response) {
            myReports.add(report)
        }
    }
}