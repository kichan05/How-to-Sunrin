package com.edcan.howtosunrin.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.activity.splash.db
import com.edcan.howtosunrin.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.btnMainNextBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val questionList = db.getAllQuestion()

                withContext(Dispatchers.Main){
                    viewModel.question.value = questionList[Random.nextInt(questionList.size)]
                }
            }
        }
    }
}