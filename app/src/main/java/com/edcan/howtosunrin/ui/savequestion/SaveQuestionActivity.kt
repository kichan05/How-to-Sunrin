package com.edcan.howtosunrin.ui.savequestion

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.ObservableArrayList
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.base.BaseActivity
import com.edcan.howtosunrin.databinding.ActivitySaveQuestionBinding

class SaveQuestionActivity : BaseActivity<ActivitySaveQuestionBinding>(R.layout.activity_save_question) {

    private val viewModel : SaveQnAViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        viewModel.getSaveQuestions()

        val saveQnaListAdapter = SaveQnARecyclerAdapter()
        binding.recyclerSaveQnA.adapter = saveQnaListAdapter
    }
}