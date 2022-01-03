package com.edcan.howtosunrin.model.user

interface UserDBInterface {
    suspend fun saveUserData(userData: User) : Int
    suspend fun getUserDataById(userId : String) : User?
}