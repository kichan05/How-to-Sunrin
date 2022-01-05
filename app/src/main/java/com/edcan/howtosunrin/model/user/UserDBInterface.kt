package com.edcan.howtosunrin.model.user

interface UserDBInterface {
    suspend fun saveUserData(userData: User) : Int //User클래스를 받아서 유저 정보를 저장한다.
    suspend fun getUserDataById(userId : String) : User? //user id를 받아서 해당 유저의 id를 가져온다.
}