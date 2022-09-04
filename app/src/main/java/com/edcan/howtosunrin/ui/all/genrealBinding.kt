package com.edcan.howtosunrin.ui.all

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.LottieAnimationView
import com.edcan.howtosunrin.ui.savequestion.SaveQnARecyclerAdapter
import com.edcan.howtosunrin.utill.qna.Question
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

@BindingAdapter("bindLottieJson")
fun bindLottieJson(v : LottieAnimationView, raw : Int){
    v.setAnimation(raw)
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

@BindingAdapter("bindSaveQnAItems")
fun bindSaveQnAItems(recyclerView: RecyclerView, items : ObservableArrayList<Question>?){
    val adapter = recyclerView.adapter as SaveQnARecyclerAdapter? ?: return

    if(items == null) return


    adapter.submitList(items.toMutableList())
}

@BindingAdapter("bindVisibility")
fun bindVisibility(v : View, visibility : Boolean){
    v.visibility = if(visibility) View.VISIBLE else View.GONE
}