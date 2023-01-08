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

lateinit var qnaDB: DB
lateinit var userDB: UserDB
lateinit var chatDB: ChatDB
lateinit var saveQuestionDB: QuestionDatabase

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        qnaDB = DB()
        userDB = UserDB()
        chatDB = ChatDB()
        saveQuestionDB = QuestionDatabase.getInstance(applicationContext)

        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            val intent = Intent(this@SplashActivity, OnBoardActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.anim_slide_up_before, R.anim.anim_slide_up)

            finish()
        }
    }
}