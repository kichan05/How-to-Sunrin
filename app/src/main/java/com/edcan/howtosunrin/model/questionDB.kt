package com.edcan.howtosunrin.model

interface questionDB {
    suspend fun getAllQuestion() : List<QandA>
}