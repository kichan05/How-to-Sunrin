package com.edcan.howtosunrin.screen.qna

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edcan.howtosunrin.model.qna.Question
import com.edcan.howtosunrin.screen.splash.db
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QnAActivityViewModel : ViewModel() {
    val question = MutableLiveData(Question())

    suspend fun getOneQuestion() : Question{
        val questions = db.getAllQuestion()

        return questions.random()
    }

    fun question() {
        CoroutineScope(Dispatchers.IO).launch {
            question.value = getOneQuestion()
        }
    }
}