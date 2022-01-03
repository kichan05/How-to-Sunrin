package com.edcan.howtosunrin.model.qna

interface questionDB {
    suspend fun getAllQuestion() : List<Question>
}