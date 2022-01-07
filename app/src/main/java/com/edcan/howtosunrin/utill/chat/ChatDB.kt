package com.edcan.howtosunrin.utill.chat

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ChatDB : ChatDBInterface {
    val db = FirebaseFirestore.getInstance()

    override suspend fun sendChat(chatData : Chat): Int {
        var result : Int = ChatUtil.ResultFail

        db.collection(ChatUtil.groupChatCollection).document().set(chatData)
            .addOnSuccessListener {
                result = ChatUtil.ResultSuccess
            }.await()

        return result
    }

    override suspend fun getChatData(): MutableList<Chat> {
        var datas: MutableList<Chat> = mutableListOf()
        db.collection(ChatUtil.groupChatCollection).get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val chatdata = document.toObject(Chat::class.java)
                    datas.add(chatdata)
                }
            }.await()

        return datas
    }

}