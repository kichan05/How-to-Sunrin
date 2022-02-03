package com.edcan.howtosunrin.ui.savequestion

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.edcan.howtosunrin.ui.chat.recycler.GroupChatRecyclerAdapter
import com.edcan.howtosunrin.utill.qna.Question

class SaveQnARecyclerAdapter() : ListAdapter<Question, RecyclerView.ViewHolder>(MainDiffUtilCallback()) {

    class MainDiffUtilCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean = oldItem.question == newItem.question

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SaveQnARecyclerHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SaveQnARecyclerHolder).bind(getItem(position))
    }

}