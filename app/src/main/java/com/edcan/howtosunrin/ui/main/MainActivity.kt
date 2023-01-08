package com.edcan.howtosunrin.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.base.BaseActivity
import com.edcan.howtosunrin.databinding.ActivityMainBinding
import com.edcan.howtosunrin.ui.info.InfoActivity
import com.edcan.howtosunrin.ui.qnaSlider.QnASliderActivity
import com.edcan.howtosunrin.ui.savequestion.SaveQuestionActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding){
            imgMainIcon.setOnClickListener { gotoWebEDCAN() }

            btnMainGotoQnASlider.setOnClickListener(gotoActivityQnA)
            btnMainGotoSaveQnA.setOnClickListener(gotoActivitySaveQnA)
            btnMainGotoDeveloperInfo.setOnClickListener(gotoActivityDeveloperInfo)
        }
    }

    private val gotoActivityQnA = { _: View ->
        val intent = Intent(this, QnASliderActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.anim_slide_from_right, R.anim.anim_hold)
    }

//    private val gotoActivityChat = { _: View ->
//        val intent = Intent(this, ChatActivity::class.java)
//        intent.putExtra("userData", currentUserData)
//        startActivity(intent)
//        overridePendingTransition(R.anim.anim_slide_from_right, R.anim.anim_hold)
//    }

    private val gotoActivityDeveloperInfo = { _ : View ->
        val intent = Intent(this, InfoActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.anim_slide_from_right, R.anim.anim_hold)
    }

    private val gotoActivitySaveQnA = { _ : View ->
        val intent = Intent(this, SaveQuestionActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.anim_slide_from_right, R.anim.anim_hold)
    }
}