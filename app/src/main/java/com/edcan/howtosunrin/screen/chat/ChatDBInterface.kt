package com.edcan.howtosunrin.screen.chat

import com.edcan.howtosunrin.model.user.User
import java.sql.Timestamp

interface ChatDBInterface {
    suspend fun sendChat(chatData : Chat): Int
}