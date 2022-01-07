package com.edcan.howtosunrin.utill.chat

import java.io.Serializable
import java.util.*

data class Chat (
    val userId : String = "", //보낸 사람의 ID
    val content : String = "", //채팅 내용
    val timeStamp: Date = Date() //채팅 보낸 시간
) : Serializable