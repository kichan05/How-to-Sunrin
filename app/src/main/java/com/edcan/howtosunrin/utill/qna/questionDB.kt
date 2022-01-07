package com.edcan.howtosunrin.utill.qna

interface questionDB {
    suspend fun getAllQuestion() : List<Question> //모든 질문을 가져온다.
}