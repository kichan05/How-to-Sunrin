package com.edcan.howtosunrin.model.qna

interface questionDB {
    suspend fun getAllQuestion() : List<Question> // Firebase에 저장된 모든 QnA들을 가져온다.
}