package com.edcan.howtosunrin.screen.chat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edcan.howtosunrin.model.chat.Chat
import com.edcan.howtosunrin.model.user.User
import com.edcan.howtosunrin.screen.splash.chatDB
import java.util.*

class GroupChatActivityViewModel: ViewModel() {
    var userData = MutableLiveData(User())
    val content = MutableLiveData("")

    lateinit var chatData : Chat

    suspend fun sendChat(userId : String, timestamp : Date = Date()) : Int{
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