package com.daedongyeojido.daedae.feature.club.detail.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.daedongyeojido.daedae.R
import com.daedongyeojido.daedae.databinding.FragmentClubDetailMemberBinding
import com.daedongyeojido.daedae.feature.club.model.ClubMember

class ClubDetailMemberFragment(private val memberList: List<ClubMember>) : Fragment() {
    private lateinit var binding: FragmentClubDetailMemberBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClubDetailMemberBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ClubMemberAdapter(memberList)
        binding.recyclerClubDetailMember.adapter = adapter
        binding.recyclerClubDetailMember.layoutManager = LinearLayoutManager(activity)
    }
}