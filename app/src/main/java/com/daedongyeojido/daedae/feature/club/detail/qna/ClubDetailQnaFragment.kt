package com.daedongyeojido.daedae.feature.club.detail.qna

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daedongyeojido.daedae.R
import com.daedongyeojido.daedae.databinding.FragmentClubDetailQnaBinding
import com.daedongyeojido.daedae.feature.club.model.ClubQuestRequest
import com.daedongyeojido.daedae.feature.club.model.Question
import com.daedongyeojido.daedae.network.ApiProvider
import com.daedongyeojido.daedae.network.ClubApi
import com.daedongyeojido.daedae.util.accessToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClubDetailQnaFragment(private val qnaList: List<Question>, private val clubName: String) : Fragment() {
    private lateinit var binding: FragmentClubDetailQnaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClubDetailQnaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ClubQnaAdapter(qnaList)
        binding.recyclerClubDetailQna.adapter = adapter
        binding.recyclerClubDetailQna.layoutManager = LinearLayoutManager(activity)

        binding.recyclerClubDetailQna.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = binding.recyclerClubDetailQna.layoutManager
                val lastVisibleItemPosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val itemCount = binding.recyclerClubDetailQna.adapter?.itemCount ?: 0

                if (lastVisibleItemPosition == itemCount - 1) {
                    binding.btnClubDetailQna.visibility = View.VISIBLE
                } else {
                    binding.btnClubDetailQna.visibility = View.GONE
                }

                val dialogLayout = View.inflate(activity, R.layout.question_dialog, null)
                binding.btnClubDetailQna.setOnClickListener {
                    AlertDialog.Builder(activity)
                        .setView(dialogLayout)
                        .setPositiveButton("질문하기", DialogInterface.OnClickListener { _, _ ->
                            val question = dialogLayout.findViewById<EditText>(R.id.edit_question_dialog).text.toString()
                            if (question.isNotEmpty()) {
                                quest(question)
                            } else {
                                Toast.makeText(activity, "질문을 입력해주세요", Toast.LENGTH_SHORT).show()
                            }
                        })
                        .setPositiveButton("취소", DialogInterface.OnClickListener { dialog, _ ->
                            dialog.dismiss()
                        })
                        .show()
                }
            }
        })
    }
    private fun quest(question: String) {
        val apiProvider = ApiProvider.getRetrofit().create(ClubApi::class.java)
        apiProvider.clubQuest(accessToken, ClubQuestRequest(clubName, question)).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.d("server", response.code().toString())
                if (response.isSuccessful) {
                    Toast.makeText(activity, "질문이 전송되었습니다", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d("server", response.message().toString())
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("server", t.message.toString())
                Toast.makeText(activity, "서버 통신 실패", Toast.LENGTH_SHORT).show()
            }
        })
    }
}