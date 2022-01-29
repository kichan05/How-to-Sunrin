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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.base.BaseActivity
import com.edcan.howtosunrin.databinding.ActivityQnaSliderBinding
import com.edcan.howtosunrin.utill.qna.Question
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

            btnQnaShare.setOnClickListener{shareQuestion()}
        }
    }

    fun shareQuestion(){
        val questionData = (viewModel.questionList[viewModel.currentPage] as QnAFragment).question

        val shareMsg = "[How to Sunrin]\nQ.${questionData.question}\nA.${questionData.answer}\n\n\n더 많은 선린생활을 알고싶다면 How to Sunrin앱을 설치하세요."

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/html"
            //putExtra(Intent.EXTRA_TEXT, Html.fromHtml("<h1>Hello EDCAN</h1>", Html.FROM_HTML_MODE_LEGACY).toString())
            putExtra(Intent.EXTRA_TEXT, shareMsg)
        }

        startActivity(Intent.createChooser(shareIntent, "질문을 공유하세요!"))
    }
}