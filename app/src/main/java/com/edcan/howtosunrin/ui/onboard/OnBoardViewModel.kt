package com.edcan.howtosunrin.ui.onboard

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2

class OnBoardViewModel : ViewModel() {
    val currentPage = MutableLiveData(0)
    var pageCount = 0

    val viewPagerCallBack = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            currentPage.value = position
        }
    }
}