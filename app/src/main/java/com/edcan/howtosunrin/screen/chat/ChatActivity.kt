package com.edcan.howtosunrin.screen.chat

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.databinding.ActivityGroupChatBinding
import com.edcan.howtosunrin.utill.SharedUtil
import com.edcan.howtosunrin.utill.chat.Chat
import com.edcan.howtosunrin.utill.chat.ChatUtil
import com.edcan.howtosunrin.screen.chat.recycler.GroupChatRecyclerAdpter
import com.edcan.howtosunrin.screen.splash.chatDB
import com.edcan.howtosunrin.utill.user.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class ChatActivity : AppCompatActivity() {
    lateinit var binding : ActivityGroupChatBinding
    lateinit var viewModel: ChatActivityViewModel
    lateinit var groupChat_RecyclerAdapter: GroupChatRecyclerAdpter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_group_chat)
        viewModel = ViewModelProvider(this).get(ChatActivityViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.userData.value = intent.getSerializableExtra("userData") as User

        groupChat_RecyclerAdapter = GroupChatRecyclerAdpter(this, viewModel.userData.value!!)
        binding.recyclerGroupchat.adapter = groupChat_RecyclerAdapter

        binding.imgChatPrevBtn.setOnClickListener{
            finish()
        }

        CoroutineScope(Dispatchers.Main).launch {
            chatDB.db.collection("ChatGroup").orderBy("timeStamp")
                .addSnapshotListener { value, error ->
                    if(error != null){
                        Toast.makeText(this@ChatActivity, "채팅 에러 발생", Toast.LENGTH_LONG).show()
                        finish()

                        return@addSnapshotListener
                    }

                    if(value == null){
                        Toast.makeText(this@ChatActivity, "채팅 값이 없습니다.", Toast.LENGTH_LONG).show()
                        finish()

                        return@addSnapshotListener
                    }

                    val chatList = mutableListOf<Chat>()

                    for(doc in value){
                        chatList.add(doc.toObject(Chat::class.java))
                    }

                    groupChat_RecyclerAdapter.chatData = chatList
                    groupChat_RecyclerAdapter.notifyDataSetChanged()
                }
        }
        

        binding.btnGchatSend.setOnClickListener {
            if(viewModel.content.value!!.isEmpty()) {
                Toast.makeText(this, "채팅 내용을 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.IO).launch {
                val result = viewModel.sendChat()

                withContext(Dispatchers.Main) {
                    if(result == ChatUtil.ResultSuccess) {
//                        Toast.makeText(applicationContext, "성공적으로 채팅을 보냈습니다!", Toast.LENGTH_SHORT).show()
                        binding.recyclerGroupchat.scrollToPosition(groupChat_RecyclerAdapter.itemCount - 1)
                        binding.message.text = null
                    } else {
                        Toast.makeText(applicationContext, "채팅을 보내지 못했습니다.", Toast.LENGTH_SHORT).show()
//                        binding.message.text = null
                    }
                }
            }
        }
    }
}