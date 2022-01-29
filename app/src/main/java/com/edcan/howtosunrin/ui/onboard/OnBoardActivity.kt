package com.edcan.howtosunrin.ui.onboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.base.BaseActivity
import com.edcan.howtosunrin.databinding.ActivityOnBoardBinding
import com.edcan.howtosunrin.ui.all.ViewPagerAdapter
import com.edcan.howtosunrin.ui.userData.UserDataActivity
import com.edcan.howtosunrin.utill.OnBoard

class OnBoardActivity : BaseActivity<ActivityOnBoardBinding>(R.layout.activity_on_board) {

    val viewModel : OnBoardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        val fragments = getFragments()
        viewModel.pageCount = fragments.size

        with(binding){
            viewPagerOnBoard.adapter = ViewPagerAdapter(this@OnBoardActivity, fragments)
            viewPagerOnBoard.registerOnPageChangeCallback(viewModel!!.viewPagerCallBack)

            txtOnBoardSkip.setOnClickListener { finish() }
            btnOnBoardStart.setOnClickListener { finish() }
        }
    }

    override fun finish() {
        val intent = Intent(this, UserDataActivity::class.java)
        startActivity(intent)

        super.finish()
    }

    fun getFragments() : List<Fragment> = listOf(
        OnBoardFragment(OnBoard(
            "https://assets4.lottiefiles.com/packages/lf20_tcwozhzv/MarketingCampaignsViralMethods.json",
            "설레이는 선린 생활",
            "정말 오고싶었던 선린\n선린 생활이 기대되지 않으신가요?"
        )),
        OnBoardFragment(OnBoard(
            "https://assets4.lottiefiles.com/packages/lf20_hi95bvmx/WebdesignBg.json",
            "궁금한게 많은 선린생활",
            "어떻게 하면 선린생활을\n잘할수있을지 알고싶으신가요?"
        )),
        OnBoardFragment(OnBoard(
            "https://assets3.lottiefiles.com/packages/lf20_y5kf5v3b.json",
            "How to Sunrin\n을 사용해보세요",
            "How to Sunrin에서는\n선린생활에 대한\n다양한 궁금증을 해결할수있습니다."
        )),
    )
}