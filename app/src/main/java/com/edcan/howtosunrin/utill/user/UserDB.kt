package com.edcan.howtosunrin.utill.user

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserDB : UserDBInterface{
    val db = FirebaseFirestore.getInstance()

    override suspend fun saveUserData(userData: User) : Int {
        var result : Int = UserUtil.ResultFail

        db.collection(UserUtil.userCollection).document(userData.userID).set(userData)
            .addOnSuccessListener {
                result = UserUtil.ResultSuccess
            }.await()

        return result
    }

    override suspend fun getUserDataById(userId: String): User? {
        var result : User? = null
        db.collection(UserUtil.userCollection).document(userId).get()
            .addOnSuccessListener {
                result = it.toObject(User::class.java)
            }.await()


        return result
    }
}