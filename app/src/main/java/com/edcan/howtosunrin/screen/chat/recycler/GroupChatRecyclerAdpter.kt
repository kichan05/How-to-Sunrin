package com.edcan.howtosunrin.screen.chat.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.model.chat.Chat

class GroupChatRecyclerAdpter(private val context: Context) : RecyclerView.Adapter<GroupChatRecyclerAdpter.GroupChatViewHolder>() {

    var data = mutableListOf<Chat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupChatRecyclerAdpter.GroupChatViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.layout_groupchat_recycleritem,
            parent, false
        )

        return GroupChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroupChatRecyclerAdpter.GroupChatViewHolder, position: Int) {
        holder.onBind((data[position]))
    }

    override fun getItemCount(): Int = data.size

    inner class GroupChatViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val userId : TextView = itemView.findViewById(R.id.txt_userId)
        private val content : TextView = itemView.findViewById(R.id.txt_content)

        init {
            itemView.setOnClickListener(this)
        }


        fun onBind(data: Chat) {
            userId.text = data.userId
            content.text = data.content
        }

        override fun onClick(v: View?) {
        }
    }
}

interface GroupChatInterface {
    fun onItemClicked(position: Int)
}