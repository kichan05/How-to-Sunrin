package com.edcan.howtosunrin.ui.qna

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.databinding.ActivityQnaBinding

class QnAActivity : AppCompatActivity() {
    lateinit var binding : ActivityQnaBinding
    lateinit var viewModel: QnAViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qna)
        viewModel = ViewModelProvider(this).get(QnAViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}