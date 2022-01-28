package com.edcan.howtosunrin.ui.chat

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.base.BaseActivity
import com.edcan.howtosunrin.databinding.ActivityGroupChatBinding
import com.edcan.howtosunrin.utill.chat.Chat
import com.edcan.howtosunrin.utill.chat.ChatUtil
import com.edcan.howtosunrin.ui.chat.recycler.GroupChatRecyclerAdapter
import com.edcan.howtosunrin.ui.splash.chatDB
import com.edcan.howtosunrin.utill.user.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class ChatActivity : BaseActivity<ActivityGroupChatBinding>(R.layout.activity_group_chat) {
    //todo
    lateinit var viewModel: ChatActivityViewModel
    lateinit var groupChat_RecyclerAdapter: GroupChatRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //todo
        viewModel = ViewModelProvider(this).get(ChatActivityViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.userData.value = intent.getSerializableExtra("userData") as User

        groupChat_RecyclerAdapter = GroupChatRecyclerAdapter(viewModel.userData.value!!)
        binding.recyclerGroupchat.adapter = groupChat_RecyclerAdapter

        chatDB.db.collection("ChatGroup").orderBy("timeStamp")
            .addSnapshotListener { value, error ->
                if(error != null){
                    Toast.makeText(this@ChatActivity, "채팅 에러 발생", Toast.LENGTH_LONG).show()
                    finish()
                    return@addSnapshotListener
                }
                if(value == null){
                    Toast.makeText(this@ChatActivity, "서버로 부터 받은 채팅 내용이 없습니다.", Toast.LENGTH_LONG).show()
                    finish()
                    return@addSnapshotListener
                }

                val chatList = mutableListOf<Chat>()
                for(doc in value){
                    chatList.add(doc.toObject(Chat::class.java))
                }

                groupChat_RecyclerAdapter.submitList(chatList)
            }


        with(binding){
            btnGchatSend.setOnClickListener(chatSend)
            imgChatEDCANIcon.setOnClickListener(gotoWebEDCAN)
            imgChatPrevBtn.setOnClickListener{ finish() }
        }
    }

    private val chatSend : (View) -> Unit = { _ : View ->
        if(viewModel.content.value!!.isEmpty()) Toast.makeText(this, "채팅 내용을 입력해주세요", Toast.LENGTH_SHORT).show()
        else CoroutineScope(Dispatchers.IO).launch {
            val result = viewModel.sendChat()

            withContext(Dispatchers.Main) {
                if(result == ChatUtil.ResultSuccess) binding.message.text = null
                else Toast.makeText(applicationContext, "채팅을 보내지 못했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}