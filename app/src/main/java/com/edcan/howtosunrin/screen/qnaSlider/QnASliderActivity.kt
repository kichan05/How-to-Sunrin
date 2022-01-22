package com.edcan.howtosunrin.screen.qna

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.databinding.ActivityQnaBinding
import com.edcan.howtosunrin.databinding.ActivityQnaSliderBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QnASliderActivity : AppCompatActivity() {
    lateinit var binding : ActivityQnaSliderBinding
    lateinit var viewModel: QnASliderActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qna_slider)
        viewModel = ViewModelProvider(this).get(QnASliderActivityViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.imgQnaPrev.setOnClickListener {
            finish()
        }

        binding.imgQnaEdcanIcon.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://edcan.kr"))
            startActivity(intent)
        }

        CoroutineScope(Dispatchers.IO).launch {
            val questionFragments = mutableListOf<Fragment>()

            val questions = viewModel.getAllQuestion().shuffled()
            for(i in questions){
                questionFragments.add(QnAFragment(i))
            }

            withContext(Dispatchers.Main){
                binding.vpQna.adapter = ViewPagerAdapter(this@QnASliderActivity, questionFragments)
            }
        }
    }
}