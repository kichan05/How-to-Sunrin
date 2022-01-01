package com.edcan.howtosunrin.screen.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.databinding.ActivityMainBinding
import com.edcan.howtosunrin.databinding.ActivityQnaBinding
import com.edcan.howtosunrin.screen.qna.QnAActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnMainGotoQnA.setOnClickListener {
            val intent = Intent(this, QnAActivity::class.java)
            startActivity(intent)
        }
    }
}