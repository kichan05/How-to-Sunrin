package com.edcan.howtosunrin.screen.chat.recycler

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.utill.chat.Chat
import com.edcan.howtosunrin.utill.user.User
import com.edcan.howtosunrin.screen.splash.userDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GroupChatRecyclerAdpter(private val context: Context, private val currentUser: User) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var chatData = mutableListOf<Chat>()
    val userData = mutableMapOf<String, User>()

    override fun getItemViewType(position: Int): Int {
        return if(chatData[position].userId == currentUser.userID){
            return CHAT_ITEM_VIEW_TYPE.MY.ordinal
        }   else{
            CHAT_ITEM_VIEW_TYPE.YOU.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
//        val a = when(viewType){
//            CHAT_ITEM_VIEW_TYPE.YOU.ordinal -> myChatViewHolder(LayoutInflater.from(context).inflate(
//                R.layout.layout_i_chat_recycleritem,
//                parent, false
//            ))
//            else -> youChatViewHolder(LayoutInflater.from(context).inflate(
//                R.layout.layout_i_chat_recycleritem, parent, false
//            ))
//        }

        return myChatViewHolder(LayoutInflater.from(context).inflate(
            R.layout.layout_i_chat_recycleritem,
            parent, false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as myChatViewHolder).onBind(chatData[position])
        return
        when(holder){
            is myChatViewHolder -> {
                (holder as myChatViewHolder).onBind(chatData[position])
                Log.d("chatData", chatData[position].toString())
            }
            else -> {
                (holder as youChatViewHolder).onBind(chatData[position])
                Log.d("chatData", chatData[position].toString())
            }
        }

    }

    override fun getItemCount(): Int = chatData.size

    inner class youChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val userId = itemView.findViewById<TextView>(R.id.txt_youChat_userName)
        private val content = itemView.findViewById<TextView>(R.id.txt_youChat_content)

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
    }

    inner class myChatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val userId : TextView = itemView.findViewById(R.id.txt_myChat_userName)
        private val content : TextView = itemView.findViewById(R.id.txt_myChat_content)

        fun onBind(data: Chat) {
            Log.d("chatData", data.toString())
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
    }
}

interface GroupChatInterface {
    fun onItemClicked(position: Int)
}