package com.edcan.howtosunrin.utill.user

interface UserDBInterface {
    suspend fun saveUserData(userData: User) : Int //User클래스를 받고 해당 유저 정보를 저장한다.
    suspend fun getUserDataById(userId : String) : User? //사용자 id를 받고 해당 id의 유저 정보를 가져온다. 단 없는 유저라면 null을 반환한다.
}