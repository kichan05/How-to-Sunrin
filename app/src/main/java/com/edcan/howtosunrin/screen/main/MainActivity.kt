package com.edcan.howtosunrin.screen.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.databinding.ActivityMainBinding
import com.edcan.howtosunrin.utill.user.User
import com.edcan.howtosunrin.screen.chat.ChatActivity
import com.edcan.howtosunrin.screen.qna.QnAActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var currentUserData : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        currentUserData = intent.getSerializableExtra("userData") as User

        binding.btnMainGotoQnA.setOnClickListener {
            val intent = Intent(this, QnAActivity::class.java)
            //todo
            intent.putExtra("userData", currentUserData)
            startActivity(intent)
        }

        binding.btnMainGotoGroupChat.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra("userData", currentUserData)
            startActivity(intent)
        }

        binding.imgMainIcon.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://edcan.kr"))
            startActivity(intent)
        }
    }
}