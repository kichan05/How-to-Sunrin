package com.edcan.howtosunrin.screen.chat

import java.io.Serializable
import java.sql.Timestamp
import java.util.*

data class Chat (
    val userID : String = "",
    val content : String = "",
    val timestamp: Date = Date()
) : Serializable