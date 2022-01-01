package com.edcan.howtosunrin.screen.qanda

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edcan.howtosunrin.model.Question

class QnAActivityViewModel : ViewModel() {
    val repo = QnAActivityRepo()
    val question = MutableLiveData(Question())
}