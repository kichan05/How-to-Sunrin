package com.edcan.howtosunrin.screen.chat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edcan.howtosunrin.utill.chat.Chat
import com.edcan.howtosunrin.utill.user.User
import com.edcan.howtosunrin.screen.splash.chatDB
import java.util.*

class ChatActivityViewModel: ViewModel() {
    var userData = MutableLiveData(User())
    val content = MutableLiveData("")

    lateinit var chatData : Chat

    suspend fun sendChat() : Int{
        chatData = Chat(
            userData.value!!.userID,
            content.value.toString(),
            Date()
        )
        return chatDB.sendChat(chatData)
    }
    suspend fun getChatData(): MutableList<Chat> {
        return chatDB.getChatData()
    }
}