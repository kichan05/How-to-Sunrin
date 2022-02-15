package com.edcan.howtosunrin.ui.savequestion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edcan.howtosunrin.databinding.ItemSaveQuestionBinding
import com.edcan.howtosunrin.utill.qna.Question

class SaveQnARecyclerHolder(private val binding : ItemSaveQuestionBinding)
    : RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        ItemSaveQuestionBinding.inflate(LayoutInflater.from(parent.context), parent ,false)
    )

    init {
        binding.root.setOnClickListener {
            val dialog = SaveQuestionDialog(it.context, binding.questionData!!)
            dialog.start()
        }
    }

    fun bind(questionData : Question){
        binding.questionData = questionData
    }
}