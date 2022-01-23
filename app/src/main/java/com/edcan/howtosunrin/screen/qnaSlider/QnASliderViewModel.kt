package com.edcan.howtosunrin.screen.qnaSlider

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.edcan.howtosunrin.utill.qna.Question
import com.edcan.howtosunrin.screen.splash.qnaDB
import com.edcan.howtosunrin.utill.qna.questionDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QnASliderViewModel : ViewModel() {
//    val pageCount = MutableLiveData(0)
    val questions = MutableLiveData(mutableListOf<Question>())


    private suspend fun getAllQuestion() : List<Question>{
        return qnaDB.getAllQuestion()
    }

    suspend fun getRandomQuestions(count : Int) : List<Question>{
        return getAllQuestion().shuffled().slice(0 until count)
    }
}