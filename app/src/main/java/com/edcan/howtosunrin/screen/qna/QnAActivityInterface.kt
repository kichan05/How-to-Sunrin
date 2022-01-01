package com.edcan.howtosunrin.screen.qna

import com.edcan.howtosunrin.model.Question

interface QnAActivityInterface {
    suspend fun getAllQustion() : List<Question>
}