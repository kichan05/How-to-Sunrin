package com.edcan.howtosunrin.screen.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.screen.qanda.QnAActivity
import com.edcan.howtosunrin.model.DB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

lateinit var db : DB

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        db = DB()

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            val intent = Intent(this@SplashActivity, QnAActivity::class.java)
            startActivity(intent)
        }
    }
}