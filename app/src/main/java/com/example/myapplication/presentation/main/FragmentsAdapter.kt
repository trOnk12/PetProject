package com.example.myapplication.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentsAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    var fragmentsList: List<Fragment> = emptyList()

    override fun getItemCount(): Int = fragmentsList.size
    override fun createFragment(position: Int): Fragment = fragmentsList[position]
}