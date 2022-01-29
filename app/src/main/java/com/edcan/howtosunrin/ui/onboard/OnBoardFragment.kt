package com.edcan.howtosunrin.ui.onboard

import android.os.Bundle
import android.util.Log
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.base.BaseFragment
import com.edcan.howtosunrin.databinding.FragmentOnBoardBinding
import com.edcan.howtosunrin.utill.OnBoard

class OnBoardFragment(
    private val onBoardData : OnBoard
) : BaseFragment<FragmentOnBoardBinding>(R.layout.fragment_on_board){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun init() {
        binding.onBoardData = onBoardData
    }
}