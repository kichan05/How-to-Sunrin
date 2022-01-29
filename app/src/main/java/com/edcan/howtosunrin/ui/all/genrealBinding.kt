package com.edcan.howtosunrin.ui.all

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

@BindingConversion
fun convertBooleanToVisible(visible : Boolean) : Int {
    return if(visible) View.VISIBLE else View.GONE
}

@BindingAdapter("bindLottieUrl")
fun bindLottieUrl(v : LottieAnimationView, url : String){
    v.setAnimationFromUrl(url)
}

@BindingAdapter("bindTapLayoutMediator")
fun bindTapLayoutMediator(tabLayout: TabLayout, viewPager2: ViewPager2){
    TabLayoutMediator(tabLayout, viewPager2){ tab : TabLayout.Tab, _ ->
        tab.view.run {
            isClickable = false
            isFocusable = false
        }
    }.attach()
}