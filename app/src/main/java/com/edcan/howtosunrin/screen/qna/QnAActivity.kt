package com.edcan.howtosunrin.screen.qna

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.screen.splash.db
import com.edcan.howtosunrin.databinding.ActivityQnaBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class QnAActivity : AppCompatActivity() {
    lateinit var binding : ActivityQnaBinding
    lateinit var viewModel: QnAActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qna)
        viewModel = ViewModelProvider(this).get(QnAActivityViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        CoroutineScope(Dispatchers.IO).launch {
            val questionList = viewModel.getAllQustion()

            withContext(Dispatchers.Main){
                viewModel.question.value = questionList[Random.nextInt(questionList.size)]
            }
        }

        binding.btnMainNextBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val questionList = viewModel.getAllQustion()

                withContext(Dispatchers.Main){
                    viewModel.question.value = questionList[Random.nextInt(questionList.size)]
                }
            }
        }
    }
}