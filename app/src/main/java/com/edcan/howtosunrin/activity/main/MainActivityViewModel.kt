package com.edcan.howtosunrin.activity.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edcan.howtosunrin.model.QandA

class MainActivityViewModel : ViewModel() {
    val question = MutableLiveData(QandA())
}