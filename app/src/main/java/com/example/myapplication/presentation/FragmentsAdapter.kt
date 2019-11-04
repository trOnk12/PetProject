package com.example.myapplication.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.presentation.model.FragmentItem

class FragmentsAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    var fragmentsList: List<FragmentItem> = emptyList()

    override fun getItemCount(): Int = fragmentsList.size
    override fun createFragment(position: Int): Fragment = fragmentsList[position].fragment
}