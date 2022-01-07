package com.edcan.howtosunrin.utill.qna

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class DB : questionDB {
    val db = FirebaseFirestore.getInstance()
    override suspend fun getAllQuestion(): List<Question> {
        val quests = mutableListOf<Question>()

        db.collection("QandA").get()
            .addOnSuccessListener { qusetsions ->
                for(i in qusetsions){
                    quests.add(i.toObject(Question::class.java))
                }
            }.await()

        return quests
    }
}