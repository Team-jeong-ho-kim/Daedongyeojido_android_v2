package com.daedongyeojido.daedae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.daedongyeojido.daedae.databinding.ActivityMainBinding
import com.daedongyeojido.daedae.feature.alarm.AlarmFragment
import com.daedongyeojido.daedae.feature.club.ClubFragment
import com.daedongyeojido.daedae.feature.club.detail.ClubDetailFragment
import com.daedongyeojido.daedae.feature.club.detail.info.ClubDetailInfoFragment
import com.daedongyeojido.daedae.feature.club.detail.member.ClubDetailMemberFragment
import com.daedongyeojido.daedae.feature.club.detail.qna.ClubDetailQnaFragment
import com.daedongyeojido.daedae.feature.club.model.ClubDetailInfo
import com.daedongyeojido.daedae.feature.club.model.ClubMember
import com.daedongyeojido.daedae.feature.club.model.Question
import com.daedongyeojido.daedae.feature.home.HomeFragment
import com.daedongyeojido.daedae.feature.mypage.MypageFragment
import com.daedongyeojido.daedae.feature.mypage.model.Report
import com.daedongyeojido.daedae.feature.notice.NoticeFragment
import com.daedongyeojido.daedae.feature.notice.detail.NoticeDetailFragment
import com.daedongyeojido.daedae.feature.report.apply.ReportApplyFragment
import com.daedongyeojido.daedae.feature.report.history.ReportHistoryFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())
        binding.bottomMainNav.selectedItemId = R.id.menu_home

        binding.bottomMainNav.setOnItemSelectedListener {item ->
            replaceFragment(
                when(item.itemId) {
                    R.id.menu_club -> ClubFragment()
                    R.id.menu_notice -> NoticeFragment()
                    R.id.menu_home -> HomeFragment()
                    R.id.menu_alarm -> AlarmFragment()
                    R.id.menu_mypage -> MypageFragment()
                    else -> HomeFragment()
                }
            )
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_main, fragment).commit()
    }
    fun backFragment() {
        supportFragmentManager.popBackStack()
    }
    fun noticeDetail(noticeId: Long) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.frame_main, NoticeDetailFragment(noticeId)).commit()
    }
    fun clubDetail(clubName: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.frame_main, ClubDetailFragment(clubName)).commit()
    }
    fun clubDetailInfo(index: Int, clubInfo: ClubDetailInfo, clubMember: List<ClubMember>, qnaList: List<Question>) {
        val transaction = supportFragmentManager.beginTransaction()
        when(index) {
            0 -> transaction.replace(R.id.frame_club_detail, ClubDetailInfoFragment(clubInfo))
            1 -> transaction.replace(R.id.frame_club_detail, ClubDetailMemberFragment(clubMember))
            2 -> transaction.replace(R.id.frame_club_detail, ClubDetailQnaFragment(qnaList, clubInfo.clubName))
        }
        transaction.commit()
    }
    fun replaceReportHistoryFragment(myReports: List<Report>) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_main, ReportHistoryFragment(myReports))
        transaction.addToBackStack(null)
        transaction.commit()
    }
    fun replaceReportFragment(noticeId: Long) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_main, ReportApplyFragment(noticeId))
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun getMajor(part: String): String {
        val major = when(part) {
            "FRONT" -> "프론트엔드"
            "BACK" -> "백엔드"
            "IOS" -> "iOS"
            "AND" -> "안드로이드"
            "FLUTTER" -> "플러터"
            "EMBEDDED" -> "임베디드"
            "AI" -> "인공지능"
            "SECURITY" -> "보안"
            "DEVOPS" -> "데브옵스"
            "DESIGN" -> "디자인"
            "GAME" -> "게임 개발"
            else -> "미정"
        }
        return major
    }
}