package com.edcan.howtosunrin.model

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlin.random.Random

class DB : questionDB{
    val db = FirebaseFirestore.getInstance()
    override suspend fun getAllQuestion(): List<QandA> {
        val quests = mutableListOf<QandA>()

        db.collection("QandA").get()
            .addOnSuccessListener { qusetsions ->
                for(i in qusetsions){
                    quests.add(i.toObject(QandA::class.java))
                }
            }.await()

        return quests
    }
}