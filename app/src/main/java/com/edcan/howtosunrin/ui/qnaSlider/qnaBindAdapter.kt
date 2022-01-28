package com.edcan.howtosunrin.ui.qnaSlider

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.edcan.howtosunrin.utill.qna.Question

@BindingAdapter("bindQnAFragments")
fun bindQnAFragments(viewPager2: ViewPager2, lists : ObservableArrayList<Fragment>) {
    val adapter = viewPager2.adapter as ViewPagerAdapter? ?: return
    adapter.items = lists
    adapter.notifyDataSetChanged()
}