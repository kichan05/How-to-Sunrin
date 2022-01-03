package com.edcan.howtosunrin.screen.qna

import com.edcan.howtosunrin.model.qna.Question
import com.edcan.howtosunrin.screen.splash.db

class QnAActivityRepo : QnAActivityInterface{

    override suspend fun getAllQustion(): List<Question> {
        return db.getAllQuestion()
    }
}