package com.edcan.howtosunrin.utill.chat

interface ChatDBInterface {
    suspend fun sendChat(chatData : Chat): Int
    suspend fun getChatData(): MutableList<Chat>
}