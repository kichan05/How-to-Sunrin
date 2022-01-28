package com.edcan.howtosunrin.ui.chat.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.edcan.howtosunrin.utill.chat.Chat
import com.edcan.howtosunrin.utill.user.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class GroupChatRecyclerAdapter(val currentUser: User) : ListAdapter<Chat, RecyclerView.ViewHolder>(MainDiffUtilCallback()){
    val userDataList = mutableMapOf<String, User>()

    class MainDiffUtilCallback : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean = oldItem == newItem
    }

    override fun getItemViewType(position: Int): Int {
        return if(getItem(position).userId == currentUser.userID){
            CHAT_ITEM_VIEW_TYPE.MY.ordinal
        }   else{
            CHAT_ITEM_VIEW_TYPE.YOU.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == CHAT_ITEM_VIEW_TYPE.MY.ordinal) MyChatViewHolder(parent)
            else YouChatViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ChatViewHolderInter) with(getItem(position)){
            if(userDataList.containsKey(userId))
                holder.bind(this, userDataList[userId]!!)
            else
                CoroutineScope(Dispatchers.IO).launch {
                    val userData = holder.bind(this@with)
                    userDataList[userId] = userData
                }
        }
    }
}