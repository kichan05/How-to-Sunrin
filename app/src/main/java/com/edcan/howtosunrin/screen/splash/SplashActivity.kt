package com.edcan.howtosunrin.screen.splash

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.model.SharedUtil
import com.edcan.howtosunrin.model.qna.DB
import com.edcan.howtosunrin.model.user.UserDB
import com.edcan.howtosunrin.screen.main.MainActivity
import com.edcan.howtosunrin.screen.userData.UserDataActivity
import kotlinx.coroutines.*

lateinit var db : DB
lateinit var userDB: UserDB

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        db = DB()
        userDB = UserDB()

        SharedUtil.pref = getSharedPreferences("pref", Activity.MODE_PRIVATE)
        SharedUtil.editor = SharedUtil.pref.edit()

        CoroutineScope(Dispatchers.Main).launch {

            val userId = SharedUtil.pref.getString(SharedUtil.keyUserId, "none")

            delay(3000)

            if(userId == "none"){ //처음 실행인 경우
                val intent = Intent(this@SplashActivity, UserDataActivity::class.java)
                startActivity(intent)
            }   else{ //두번째 이상 실행인 경우
                val intent = Intent(this@SplashActivity, MainActivity::class.java)

                val userData = userDB.getUserDataById(userId!!)
                intent.putExtra("userData", userData)

                startActivity(intent)
            }


        }
    }
}