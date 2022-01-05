package com.edcan.howtosunrin.screen.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edcan.howtosunrin.model.user.User
import java.util.*

class GroupChatActivityViewModel: ViewModel() {
    val content = MutableLiveData("")

    lateinit var chatData : Chat

    suspend fun sendChat(userId : String, timestamp : Date) : Int{
        chatData = Chat(
            userId,
            content.value.toString(),
            timestamp
        )
        return chatDB.sendChat(chatData)
    }
}