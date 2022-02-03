package com.edcan.howtosunrin.ui.savequestion

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edcan.howtosunrin.ui.splash.saveQuestionDB
import com.edcan.howtosunrin.utill.qna.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SaveQnAViewModel : ViewModel() {
    val saveQuestionList = ObservableArrayList<Question>()

    fun getSaveQuestions(){
        viewModelScope.launch(Dispatchers.IO) {
            val items = saveQuestionDB.questionDao().getAll()

            saveQuestionList.addAll(items)
        }
    }
}