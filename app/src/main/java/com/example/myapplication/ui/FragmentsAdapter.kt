package com.example.myapplication.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.ui.model.FragmentItem

class FragmentsAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    var fragmentList: List<FragmentItem> = emptyList()

    override fun getItemCount(): Int = fragmentList.size
    override fun createFragment(position: Int): Fragment = fragmentList[position].fragment
}