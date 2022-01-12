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

class GroupChatRecyclerAdapter(private val context: Context, private val currentUser: User) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var chatData = mutableListOf<Chat>()
    val userData = mutableMapOf<String, User>()

    override fun getItemViewType(position: Int): Int {
        return if(chatData[position].userId == currentUser.userID){
            CHAT_ITEM_VIEW_TYPE.MY.ordinal
        }   else{
            CHAT_ITEM_VIEW_TYPE.YOU.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        return when(viewType){
            CHAT_ITEM_VIEW_TYPE.MY.ordinal -> myChatViewHolder.from(parent)
            else -> youChatViewHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val userId = chatData[position].userId

        when(holder){
            is myChatViewHolder -> {
                if(userData.containsKey(userId)){
                    holder.onBind(chatData[position], userData[userId]!!.name)
                    return
                }

                CoroutineScope(Dispatchers.IO).launch {
                    userData[userId] = userDB.getUserDataById(userId)!!
                    withContext(Dispatchers.Main){
                        holder.onBind(chatData[position], userData[userId]!!.name)
                    }
                }
                Log.d("chatData", chatData[position].toString())
            }
            else -> {
                if(userData.containsKey(userId)){
                    (holder as youChatViewHolder).onBind(chatData[position], userData[userId]!!.name)
                    return
                }

                CoroutineScope(Dispatchers.IO).launch {
                    userData[userId] = userDB.getUserDataById(userId)!!
                    withContext(Dispatchers.Main){
                        (holder as youChatViewHolder).onBind(chatData[position], userData[userId]!!.name)
                    }
                }
                Log.d("chatData", chatData[position].toString())
            }
        }







    }

    override fun getItemCount(): Int = chatData.size


    class youChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val userId = itemView.findViewById<TextView>(R.id.txt_youChat_userName)
        val content = itemView.findViewById<TextView>(R.id.txt_youChat_content)

        fun onBind(chat: Chat, userName : String) {
            content.text = chat.content
            userId.text = userName
        }

        companion object{
            fun from(parent: ViewGroup) : youChatViewHolder{
                val layout = LayoutInflater.from(parent.context).inflate(R.layout.layout_you_chat_recycleritem, parent, false)

                return youChatViewHolder(layout)
            }
        }
    }

    class myChatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val userId : TextView = itemView.findViewById(R.id.txt_myChat_userName)
        private val content : TextView = itemView.findViewById(R.id.txt_myChat_content)

        fun onBind(chat: Chat, userName : String) {
            content.text = chat.content
            userId.text = userName
        }

        companion object{
            fun from(parent: ViewGroup) : myChatViewHolder{
                val layout = LayoutInflater.from(parent.context).inflate(R.layout.layout_i_chat_recycleritem, parent, false)

                return myChatViewHolder(layout)
            }
        }
    }
}