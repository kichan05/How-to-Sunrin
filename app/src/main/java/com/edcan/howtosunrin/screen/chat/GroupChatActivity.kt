package com.edcan.howtosunrin.screen.chat

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.databinding.ActivityGroupChatBinding
import com.edcan.howtosunrin.model.SharedUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

lateinit var chatDB: ChatDB

class GroupChatActivity : AppCompatActivity() {
    lateinit var binding : ActivityGroupChatBinding
    lateinit var viewModel: GroupChatActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_group_chat)
        viewModel = ViewModelProvider(this).get(GroupChatActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        chatDB = ChatDB()

        val userId = SharedUtil.pref.getString(SharedUtil.keyUserId, "none")

        binding.btnGchatSend.setOnClickListener {
            if(viewModel.content.value!!.isEmpty()) {
                Toast.makeText(this, "채팅 내용을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    val result = viewModel.sendChat(userId.toString(), Date())
                    withContext(Dispatchers.Main) {
                        if(result == ChatUtil.ResultSuccess) {
                            Toast.makeText(applicationContext, "성공적으로 채팅을 보냈습니다!", Toast.LENGTH_SHORT).show()
                            binding.message.text = null
                        } else {
                            Toast.makeText(applicationContext, "채팅을 보내지 못했습니다.", Toast.LENGTH_SHORT).show()
                            binding.message.text = null
                        }
                    }
                }
            }
        }
    }
}