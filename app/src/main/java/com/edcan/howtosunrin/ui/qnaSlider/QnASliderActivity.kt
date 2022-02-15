package com.edcan.howtosunrin.ui.qnaSlider

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.base.BaseActivity
import com.edcan.howtosunrin.databinding.ActivityQnaSliderBinding
import com.edcan.howtosunrin.utill.qna.Question
import com.edcan.howtosunrin.utill.qna.QuestionDatabase
import com.edcan.howtosunrin.utill.qna.QuestionUtil.shareQuestion
import kotlinx.coroutines.*

class QnASliderActivity : BaseActivity<ActivityQnaSliderBinding>(R.layout.activity_qna_slider) {
    lateinit var viewModel: QnASliderViewModel
//    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(QnASliderViewModel::class.java)
        binding.viewModel = viewModel


        with(binding){
            setSupportActionBar(toolBarQua)
            toolBarQua.setNavigationOnClickListener{ finish() }

            vpQna.adapter = ViewPagerAdapter(this@QnASliderActivity, mutableListOf())
            vpQna.registerOnPageChangeCallback(viewModel!!.sliderCallback)

            btnQnaShare.setOnClickListener{ shareQuestion(this@QnASliderActivity, getCurrentQuestion()!!) }

            btnQnaSaveQuestion.setOnClickListener { saveQuestion(getCurrentQuestion()!!) }

            btnQnaRemoveQuestion.setOnClickListener { removeQuestion(getCurrentQuestion()!!) }
        }
    }

    fun saveQuestion(questionData : Question) {
        viewModel.saveQuestion(questionData)
        Toast.makeText(this, "질문을 추가했습니다.", Toast.LENGTH_LONG).show()
    }

    fun removeQuestion(questionData: Question) {
        viewModel.removeQuestion(questionData)
        Toast.makeText(this, "질문을 저장을 취소했습니다.", Toast.LENGTH_LONG).show()
    }

    fun getCurrentQuestion() = viewModel.getCurrentQuestion()
}