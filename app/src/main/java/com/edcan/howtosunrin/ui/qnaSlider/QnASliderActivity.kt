package com.edcan.howtosunrin.ui.qnaSlider

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.base.BaseActivity
import com.edcan.howtosunrin.databinding.ActivityQnaSliderBinding
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
        }
    }
}