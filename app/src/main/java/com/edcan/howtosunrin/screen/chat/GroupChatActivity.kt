package com.edcan.howtosunrin.screen.chat

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.databinding.ActivityGroupChatBinding

class GroupChatActivity : AppCompatActivity() {
    lateinit var binding : ActivityGroupChatBinding
    lateinit var viewModel: GroupChatActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_group_chat)
        viewModel = ViewModelProvider(this).get(GroupChatActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.btnGchatSend.setOnClickListener {
            if(viewModel.content.value!!.isEmpty()) {
                Toast.makeText(this, "채팅 내용을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}