package com.edcan.howtosunrin.ui.userData

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edcan.howtosunrin.utill.SharedUtil
import com.edcan.howtosunrin.utill.user.User
import com.edcan.howtosunrin.ui.splash.userDB
import java.text.SimpleDateFormat
import java.util.*

class UserDataViewModel : ViewModel() {
    val name = MutableLiveData("")
    val major = MutableLiveData(0)
    val newbie = MutableLiveData(false)

    lateinit var userData : User

    val choiceMajorCallBack = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            major.value = position
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }
    }

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