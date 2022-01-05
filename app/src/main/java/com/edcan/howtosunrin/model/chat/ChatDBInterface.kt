package com.edcan.howtosunrin.model.chat

interface ChatDBInterface {
    suspend fun sendChat(chatData : Chat): Int
    suspend fun getChatData(): MutableList<Chat>
}