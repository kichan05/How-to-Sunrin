package com.edcan.howtosunrin.screen.chat

import com.edcan.howtosunrin.model.user.User
import com.edcan.howtosunrin.model.user.UserUtil
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.sql.Timestamp

class ChatDB : ChatDBInterface {
    val db = FirebaseFirestore.getInstance()

    override suspend fun sendChat(chatData : Chat): Int {
        var result : Int = ChatUtil.ResultFail

        db.collection("ChatGroup").document().set(chatData)
            .addOnSuccessListener {
                result = ChatUtil.ResultSuccess
            }.await()

        return result
    }

    override suspend fun getChatData(): MutableList<Chat> {
        var datas: MutableList<Chat> = mutableListOf()
        db.collection("ChatGroup").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val chatdata = document.toObject(Chat::class.java)
                    datas.add(chatdata)
                }
            }.await()

        return datas
    }

}