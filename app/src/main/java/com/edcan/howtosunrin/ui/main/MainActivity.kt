package com.edcan.howtosunrin.ui.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.base.BaseActivity
import com.edcan.howtosunrin.databinding.ActivityMainBinding
import com.edcan.howtosunrin.utill.user.User
import com.edcan.howtosunrin.ui.chat.ChatActivity
import com.edcan.howtosunrin.ui.qnaSlider.QnASliderActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    lateinit var currentUserData : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentUserData = intent.getSerializableExtra("userData") as User

        with(binding){
            imgMainIcon.setOnClickListener(gotoWebEDCAN)
            btnMainGotoQnASlider.setOnClickListener(gotoActivityQnA)
            btnMainGotoGroupChat.setOnClickListener(gotoActivityChat)
        }
    }

    private val gotoActivityQnA = { _: View ->
        val intent = Intent(this, QnASliderActivity::class.java)
        startActivity(intent)
    }

    private val gotoActivityChat = { _: View ->
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("userData", currentUserData)
        startActivity(intent)
    }
}