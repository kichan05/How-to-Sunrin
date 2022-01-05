package com.edcan.howtosunrin.screen.chat.recycler

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.model.chat.Chat
import com.edcan.howtosunrin.model.user.User
import com.edcan.howtosunrin.screen.splash.userDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GroupChatRecyclerAdpter(private val context: Context) : RecyclerView.Adapter<GroupChatRecyclerAdpter.GroupChatViewHolder>() {
    var chatData = mutableListOf<Chat>()
    val userData = mutableMapOf<String, User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupChatRecyclerAdpter.GroupChatViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.layout_groupchat_recycleritem,
            parent, false
        )

        return GroupChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroupChatRecyclerAdpter.GroupChatViewHolder, position: Int) {
        holder.onBind((chatData[position]))
    }

    override fun getItemCount(): Int = chatData.size

    inner class GroupChatViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val userId : TextView = itemView.findViewById(R.id.txt_userName)
        private val content : TextView = itemView.findViewById(R.id.txt_content)

        init {
            itemView.setOnClickListener(this)
        }


        fun onBind(data: Chat) {
            content.text = data.content

            if(data.userId == "admin"){
                userId.text = "admin"
                return
            }

            if(userData.containsKey(data.userId)){
                userId.text = userData[data.userId]!!.name
                return
            }

            CoroutineScope(Dispatchers.IO).launch {
                Log.d("userId", data.userId)
                userData[data.userId] = userDB.getUserDataById(data.userId)!!
                withContext(Dispatchers.Main){
                    userId.text = userData[data.userId]!!.name
                }
            }
        }

        override fun onClick(v: View?) {
        }
    }
}

interface GroupChatInterface {
    fun onItemClicked(position: Int)
}