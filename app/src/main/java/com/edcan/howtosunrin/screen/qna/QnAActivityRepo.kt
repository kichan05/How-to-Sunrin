package com.edcan.howtosunrin.screen.qna

import com.edcan.howtosunrin.model.Question
import com.edcan.howtosunrin.screen.splash.db
import com.google.firebase.firestore.FirebaseFirestore

class QnAActivityRepo : QnAActivityInterface{

    override suspend fun getAllQustion(): List<Question> {
        return db.getAllQuestion()
    }
}