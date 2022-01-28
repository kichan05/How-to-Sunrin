package com.edcan.howtosunrin.ui.chat.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edcan.howtosunrin.databinding.ItemYouChatRecycleritemBinding
import com.edcan.howtosunrin.ui.splash.userDB
import com.edcan.howtosunrin.utill.chat.Chat
import com.edcan.howtosunrin.utill.user.User

class YouChatViewHolder(private val binding : ItemYouChatRecycleritemBinding)
    : RecyclerView.ViewHolder(binding.root), ChatViewHolderInter{

    constructor(parent : ViewGroup) : this(
        ItemYouChatRecycleritemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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