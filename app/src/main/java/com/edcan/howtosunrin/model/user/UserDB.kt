package com.edcan.howtosunrin.model.user

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserDB : UserDBInterface{
    val db = FirebaseFirestore.getInstance()

    override suspend fun saveUserData(userData: User) : Int {
        var result : Int = UserUtill.ResultFail

        db.collection("user").document(userData.userID).set(userData)
            .addOnSuccessListener {
                result = UserUtill.ResultSuccess
            }.await()

        return result
    }

    override suspend fun getUserDataById(userId: String): User? {
        var result : User? = null
        db.collection("user").document(userId).get()
            .addOnSuccessListener {
                result = it.toObject(User::class.java)
            }


        return result
    }
}