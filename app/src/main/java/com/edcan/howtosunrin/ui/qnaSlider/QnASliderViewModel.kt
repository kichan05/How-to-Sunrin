package com.edcan.howtosunrin.ui.qnaSlider

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.viewpager2.widget.ViewPager2
import com.edcan.howtosunrin.ui.all.bindSaveQnAItems
import com.edcan.howtosunrin.utill.qna.Question
import com.edcan.howtosunrin.ui.splash.qnaDB
import com.edcan.howtosunrin.ui.splash.saveQuestionDB
import com.edcan.howtosunrin.utill.qna.QuestionDatabase
import kotlinx.coroutines.*
import kotlin.math.log

class QnASliderViewModel : ViewModel() {
    val questionList = ObservableArrayList<Fragment>()

    val saveQnaList = ObservableArrayList<Question>()

    var currentPage = 0

    val isCurrentQnASave = MutableLiveData<Boolean>(false)

    init {

        viewModelScope.launch(Dispatchers.IO) {
            CoroutineScope(Dispatchers.IO).async {
                addQuestions(15)
            }.await()

            val _questions = saveQuestionDB.questionDao().getAll().toList()
            withContext(Dispatchers.Main) {
                saveQnaList.addAll(_questions)

                saveQuestionListIncludeCurrentQuestion()
            }
        }
    }

    suspend fun addQuestions(count : Int) {
        val fragments = mutableListOf<Fragment>()

        val questions = getRandomQuestions(count)
        for(i in questions) fragments.add(QnAFragment(i))

        questionList.addAll(fragments)
    }

    private suspend fun getRandomQuestions(count : Int) : List<Question>{
        return getAllQuestion().shuffled().slice(0 until count)
    }

    private suspend fun getAllQuestion() : List<Question>{
        return qnaDB.getAllQuestion()
    }


    fun saveQuestion(questionData : Question) {
        viewModelScope.launch(Dispatchers.IO){
            saveQuestionDB.questionDao().insert(questionData)

            saveQnaList.add(questionData)
            withContext(Dispatchers.Main){ saveQuestionListIncludeCurrentQuestion() }
        }
    }

    fun getCurrentQuestion() : Question? {
        return if(0 <= currentPage && currentPage < questionList.size)
            (questionList[currentPage] as QnAFragment).question
        else null
    }


    private fun saveQuestionListIncludeCurrentQuestion() {
        val currentQuestion = getCurrentQuestion()

        if(currentQuestion == null){
            isCurrentQnASave.value = false
            return
        }

        isCurrentQnASave.value = saveQnaList.any {
            it.checkEqualExcludeId(currentQuestion)
        }
    }


    val sliderCallback = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            currentPage = position

            saveQuestionListIncludeCurrentQuestion()

            if(questionList.size - position <= 7){
                viewModelScope.launch(Dispatchers.IO) {
                    addQuestions(10)
                }
            }
        }
    }
}