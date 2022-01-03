package com.edcan.howtosunrin.screen.qna

import com.edcan.howtosunrin.model.qna.Question

interface QnAActivityInterface {
    suspend fun getAllQustion() : List<Question>
}