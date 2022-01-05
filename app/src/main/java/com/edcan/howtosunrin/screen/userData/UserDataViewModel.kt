package com.edcan.howtosunrin.screen.userData

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edcan.howtosunrin.model.SharedUtil
import com.edcan.howtosunrin.model.user.User
import com.edcan.howtosunrin.model.user.UserUtil
import com.edcan.howtosunrin.screen.splash.userDB
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class UserDataViewModel : ViewModel() {
    val name = MutableLiveData("")
    val major = MutableLiveData(0)
    val newbie = MutableLiveData(false)

    lateinit var userData : User

    suspend fun saveUserData() : Int{

        val userId = getRandomUserId()
        SharedUtil.editor.putString(SharedUtil.keyUserId, userId)
        SharedUtil.editor.apply()

        userData = User(
            name = name.value!!,
            major = major.value!!,
            userID = userId,
            newbie = newbie.value!!
        )

        return userDB.saveUserData(userData)
    }



    private fun getRandomUserId(): String {
        return SimpleDateFormat("yyyy-MM-dd-yy-mm-ss").format(Date()) + "-${Random().nextInt(100000000)}"
    }

}