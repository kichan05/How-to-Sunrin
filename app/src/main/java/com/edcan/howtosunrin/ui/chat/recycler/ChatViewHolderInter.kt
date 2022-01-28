package com.edcan.howtosunrin.ui.chat.recycler

import com.edcan.howtosunrin.utill.chat.Chat
import com.edcan.howtosunrin.utill.user.User

interface ChatViewHolderInter {
    fun bind(chat : Chat, userData : User)
    suspend fun bind(chat : Chat) : User
}