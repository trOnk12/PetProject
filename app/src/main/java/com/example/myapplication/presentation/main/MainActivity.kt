package com.example.myapplication.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.presentation.comments.CommentsFragment
import com.example.myapplication.presentation.feed.FeedFragment
import com.example.myapplication.presentation.friends.FriendsFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : FragmentActivity() {
    companion object {
        fun callingIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    var fragmentsList = listOf(CommentsFragment(), FeedFragment(), FriendsFragment())
    var fragmentsTitle = listOf("Comments", "Feed", "Friends")

    lateinit var viewPager: ViewPager2
    lateinit var fragmentsAdapter: FragmentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initializeView()
    }

    private fun initializeView() {
        fragmentsAdapter = FragmentsAdapter(this)
        fragmentsAdapter.fragmentsList = this.fragmentsList

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = fragmentsAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = fragmentsTitle[position]
        }.attach()

        toolBar.attachHostActivity(this)
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }


}