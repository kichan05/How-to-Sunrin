package com.edcan.howtosunrin.ui.all

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val items : List<Fragment>) : FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment = items[position]

}