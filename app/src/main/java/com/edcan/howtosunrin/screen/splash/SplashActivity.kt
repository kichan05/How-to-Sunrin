package com.edcan.howtosunrin.screen.splash

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.utill.SharedUtil
import com.edcan.howtosunrin.utill.chat.ChatDB
import com.edcan.howtosunrin.utill.qna.DB
import com.edcan.howtosunrin.utill.user.UserDB
import com.edcan.howtosunrin.screen.main.MainActivity
import com.edcan.howtosunrin.screen.userData.UserDataActivity
import kotlinx.coroutines.*

lateinit var qnaDB : DB
lateinit var userDB: UserDB
lateinit var chatDB: ChatDB

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        qnaDB = DB()
        userDB = UserDB()
        chatDB = ChatDB()

        SharedUtil.pref = getSharedPreferences("pref", Activity.MODE_PRIVATE)
        SharedUtil.editor = SharedUtil.pref.edit()

        CoroutineScope(Dispatchers.Main).launch {

            val userId = SharedUtil.pref.getString(SharedUtil.keyUserId, "none")

            if(userId == "none"){ //처음 실행인 경우
                delay(3000)
                val intent = Intent(this@SplashActivity, UserDataActivity::class.java)
                startActivity(intent)
                finish()
            }   else{ //두번째 이상 실행인 경우
                //todo 1 아래에 들어갈 코드는??
                val intent = Intent(this@SplashActivity, MainActivity::class.java)

                val userData = userDB.getUserDataById(userId!!)
                intent.putExtra("userData", userData)

                Log.d("userData", userId)
                Log.d("userData", userData.toString())
                //todo 2 log.d의 d는 무엇의 약자? log에는 또 어떤 것이 표시됨?


                //todo 3 인텐트 후 finish가 있는 이유가 뭘까
                startActivity(intent)
                finish()
            }


        }
    }
}