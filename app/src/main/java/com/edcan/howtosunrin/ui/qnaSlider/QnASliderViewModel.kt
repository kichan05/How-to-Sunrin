package com.edcan.howtosunrin.ui.qnaSlider

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.edcan.howtosunrin.utill.qna.Question
import com.edcan.howtosunrin.ui.splash.qnaDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QnASliderViewModel : ViewModel() {
    val questionList = ObservableArrayList<Fragment>()

    init {
        addQuestions(15)
    }

    val sliderCallback = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

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
}