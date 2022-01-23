package com.edcan.howtosunrin.screen.qnaSlider

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.databinding.ActivityQnaSliderBinding
import com.edcan.howtosunrin.utill.qna.Question
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QnASliderActivity : AppCompatActivity() {
    lateinit var binding : ActivityQnaSliderBinding
    lateinit var viewModel: QnASliderViewModel

    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qna_slider)
        viewModel = ViewModelProvider(this).get(QnASliderViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.imgQnaPrev.setOnClickListener {
            finish()
        }

        binding.imgQnaEdcanIcon.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://edcan.kr"))
            startActivity(intent)
        }

        //binding.vpQna.registerOnPageChangeCallback(viewModel.sliderCallback)

        viewPagerAdapter = ViewPagerAdapter(this, mutableListOf())
        binding.vpQna.adapter = viewPagerAdapter


        val sliderCallback = object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if(viewPagerAdapter.itemCount - position <= 7){
                    addQuestions(10)
                }
            }
        }

        binding.vpQna.registerOnPageChangeCallback(sliderCallback)

        addQuestions(15)
    }

    fun addQuestions(count : Int){
        CoroutineScope(Dispatchers.IO).launch {
            val questions = viewModel.getRandomQuestions(count)

            withContext(Dispatchers.Main){
                for(i in questions){
                    viewPagerAdapter.addFragments(QnAFragment(i))
                    viewPagerAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}