package com.edcan.howtosunrin.screen.chat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class GroupChatActivityViewModel: ViewModel() {
    val content = MutableLiveData("")

    lateinit var chatData : Chat

    suspend fun sendChat(userId : String, timestamp : String) : Int{
        chatData = Chat(
            userId,
            content.value.toString(),
            timestamp
        )
        return chatDB.sendChat(chatData)
    }
    suspend fun getChatData(): MutableList<Chat> {
        return chatDB.getChatData()
    }
}