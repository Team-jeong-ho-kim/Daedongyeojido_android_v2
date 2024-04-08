package com.daedongyeojido.daedae.feature.club

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.daedongyeojido.daedae.MainActivity
import com.daedongyeojido.daedae.R
import com.daedongyeojido.daedae.databinding.FragmentClubBinding
import com.daedongyeojido.daedae.feature.club.model.ClubInfo
import com.daedongyeojido.daedae.feature.club.model.ClubResponse
import com.daedongyeojido.daedae.network.ApiProvider
import com.daedongyeojido.daedae.network.ClubApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClubFragment : Fragment(), ClubAdapter.ClubClickListener {
    private lateinit var binding: FragmentClubBinding
    private val clubList = mutableListOf<ClubInfo>()
    private val adapter = ClubAdapter(clubList, this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClubBinding.inflate(layoutInflater, container, false)
        clubList.clear()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerClub.adapter = adapter
        binding.recyclerClub.layoutManager = LinearLayoutManager(activity)

        getClubInfo()
    }

    private fun getClubInfo() {
        val apiProvider = ApiProvider.getRetrofit().create(ClubApi::class.java)
        apiProvider.getClubList().enqueue(object : Callback<ClubResponse> {
            override fun onResponse(call: Call<ClubResponse>, response: Response<ClubResponse>) {
                Log.d("server", response.code().toString())
                if (response.isSuccessful) {
                    Log.d("server", response.body().toString())
                    response.body()?.allClubResponses?.let { setClubInfo(it) }
                } else {
                    Log.d("server", response.message().toString())
                }
            }

            override fun onFailure(call: Call<ClubResponse>, t: Throwable) {
                Log.d("server", t.message.toString())
            }
        })
    }

    private fun setClubInfo(response: List<ClubInfo>) {
        for(info in response) {
            clubList.add(info)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onClubClicked(clubName: String) {
        (activity as MainActivity).clubDetail(clubName)
    }
}