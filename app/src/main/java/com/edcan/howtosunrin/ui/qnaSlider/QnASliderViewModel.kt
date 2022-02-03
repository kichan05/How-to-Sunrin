package com.edcan.howtosunrin.ui.qnaSlider

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.viewpager2.widget.ViewPager2
import com.edcan.howtosunrin.utill.qna.Question
import com.edcan.howtosunrin.ui.splash.qnaDB
import com.edcan.howtosunrin.ui.splash.saveQuestionDB
import com.edcan.howtosunrin.utill.qna.QuestionDatabase
import kotlinx.coroutines.*

class QnASliderViewModel : ViewModel() {
    val questionList = ObservableArrayList<Fragment>()

    var currentPage = 0

    init {
        addQuestions(15)
    }

    val sliderCallback = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            currentPage = position

            if(questionList.size - position <= 7){
                addQuestions(10)
            }
        }
    }

    private suspend fun getAllQuestion() : List<Question>{
        return qnaDB.getAllQuestion()
    }

    private suspend fun getRandomQuestions(count : Int) : List<Question>{
        return getAllQuestion().shuffled().slice(0 until count)
    }

    fun addQuestions(count : Int) = CoroutineScope(Dispatchers.IO).launch {
        val fragments = mutableListOf<Fragment>()

        val questions = getRandomQuestions(count)
        for(i in questions) fragments.add(QnAFragment(i))

        questionList.addAll(fragments)
    }

    fun saveQuestion(questionData : Question) {
        viewModelScope.launch(Dispatchers.IO){
            saveQuestionDB.questionDao().insert(questionData)
        }
    }

    fun getCurrentQuestion() : Question = (questionList[currentPage] as QnAFragment).question
}