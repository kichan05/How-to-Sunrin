package com.edcan.howtosunrin.screen.qna

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edcan.howtosunrin.model.Question

class QnAActivityViewModel : ViewModel(), QnAActivityInterface {
    val repo = QnAActivityRepo()
    val question = MutableLiveData(Question())


    override suspend fun getAllQustion(): List<Question> {
        return repo.getAllQustion()
    }
}