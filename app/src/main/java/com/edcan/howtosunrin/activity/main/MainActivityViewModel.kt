package com.edcan.howtosunrin.activity.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edcan.howtosunrin.model.Question

class MainActivityViewModel : ViewModel() {
    val question = MutableLiveData(Question())
}