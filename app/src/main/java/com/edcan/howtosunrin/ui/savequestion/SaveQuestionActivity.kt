package com.edcan.howtosunrin.ui.savequestion

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.base.BaseActivity
import com.edcan.howtosunrin.databinding.ActivitySaveQuestionBinding
import com.edcan.howtosunrin.ui.all.recyclerDeco.VerticalSpaceItemDecoration
import com.edcan.howtosunrin.ui.qnaSlider.QnASliderActivity

class SaveQuestionActivity : BaseActivity<ActivitySaveQuestionBinding>(R.layout.activity_save_question) {

    private val viewModel : SaveQnAViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        setSupportActionBar(binding.toolBarSaveQnA)
        binding.toolBarSaveQnA.setNavigationOnClickListener{ finish() }

        binding.btnSaveQnAGotoQnA.setOnClickListener {
            val intent = Intent(this, QnASliderActivity::class.java)
            startActivity(intent)
        }

        viewModel.getSaveQuestions()

        val saveQnaListAdapter = SaveQnARecyclerAdapter()
        binding.recyclerSaveQnA.adapter = saveQnaListAdapter
//        binding.recyclerSaveQnA.addItemDecoration(VerticalSpaceItemDecoration(20))
        binding.recyclerSaveQnA.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager(this).orientation))
    }
}