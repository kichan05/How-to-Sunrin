package com.edcan.howtosunrin.screen.chat.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edcan.howtosunrin.databinding.ItemIChatRecycleritemBinding
import com.edcan.howtosunrin.screen.splash.userDB
import com.edcan.howtosunrin.utill.chat.Chat
import com.edcan.howtosunrin.utill.user.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MyChatViewHolder(private val binding : ItemIChatRecycleritemBinding)
    : RecyclerView.ViewHolder(binding.root), ChatViewHolderInter{

    constructor(parent : ViewGroup) : this(
        ItemIChatRecycleritemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun bind(chatData : Chat, userData : User){
        binding.chatData = chatData
        binding.userData = userData
    }

    override suspend fun bind(chatData : Chat) : User{
        val userData = userDB.getUserDataById(chatData.userId)

        binding.chatData = chatData
        binding.userData = userData

        return userData!!
    }
}