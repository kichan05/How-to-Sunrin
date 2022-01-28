package com.edcan.howtosunrin.ui.qnaSlider

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity, var items : MutableList<Fragment>): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment = items[position]

    fun addFragments(item : Fragment) = items.add(item)

    fun addFragments(items: List<Fragment>){
        for(i in items) this.items.add(i)
    }
}