package com.edcan.howtosunrin.ui.savequestion

import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.view.Window
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.databinding.LayoutQuestionBinding
import com.edcan.howtosunrin.dialog.SaveQuestionDialog
import com.edcan.howtosunrin.utill.qna.Question

class SaveQnARecyclerHolder(private val binding : LayoutQuestionBinding)
    : RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        LayoutQuestionBinding.inflate(LayoutInflater.from(parent.context), parent ,false)
    )

    init {
        binding.root.setOnClickListener {
            val dialog = SaveQuestionDialog(it.context, binding.questionData!!)
            dialog.start(it.context)

//            Toast.makeText(binding.root.context, binding.questionData!!.question, Toast.LENGTH_LONG).show()
        }
    }

    fun bind(questionData : Question){
        binding.questionData = questionData
    }
}