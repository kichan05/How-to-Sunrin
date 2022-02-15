package com.edcan.howtosunrin.ui.splash

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.utill.SharedUtil
import com.edcan.howtosunrin.utill.chat.ChatDB
import com.edcan.howtosunrin.utill.qna.DB
import com.edcan.howtosunrin.utill.user.UserDB
import com.edcan.howtosunrin.ui.main.MainActivity
import com.edcan.howtosunrin.ui.onboard.OnBoardActivity
import com.edcan.howtosunrin.utill.qna.QuestionDatabase
import kotlinx.coroutines.*

lateinit var qnaDB : DB
lateinit var userDB: UserDB
lateinit var chatDB: ChatDB

lateinit var saveQuestionDB : QuestionDatabase

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        qnaDB = DB()
        userDB = UserDB()
        chatDB = ChatDB()

        saveQuestionDB = QuestionDatabase.getInstance(applicationContext)

        SharedUtil.pref = getSharedPreferences("pref", Activity.MODE_PRIVATE)
        SharedUtil.editor = SharedUtil.pref.edit()


        CoroutineScope(Dispatchers.Main).launch {
            val userId = SharedUtil.pref.getString(SharedUtil.keyUserId, "none")

            if(userId == "none"){ //처음 실행인 경우
                delay(3000)


                val intent = Intent(this@SplashActivity, OnBoardActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.anim_slide_up_before, R.anim.anim_slide_up)

                finish()
            }   else{ //두번째 이상 실행인 경우
                delay(700)

                val intent = Intent(this@SplashActivity, MainActivity::class.java)

                val userData = userDB.getUserDataById(userId!!)
                intent.putExtra("userData", userData)
                startActivity(intent)
                overridePendingTransition(R.anim.anim_slide_up_before, R.anim.anim_slide_up)

                finish()
            }


        }
    }
}