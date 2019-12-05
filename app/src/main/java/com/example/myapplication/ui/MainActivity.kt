package com.example.myapplication.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.ui.comments.CommentsFragment
import com.example.myapplication.ui.feed.FeedFragment
import com.example.myapplication.ui.friends.FriendsFragment
import com.example.myapplication.ui.model.FragmentItem
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : FragmentActivity() {
    companion object {
        fun callingIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

    private var fragmentsItems = listOf(
        FragmentItem("Comments", CommentsFragment()),
        FragmentItem("Feed", FeedFragment()),
        FragmentItem(("Friends"), FriendsFragment())
    )

    private lateinit var fragmentsAdapter: FragmentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initializeView()
    }

    private fun initializeView() {
        toolBar.attachHostActivity(this)

        fragmentsAdapter = FragmentsAdapter(this)
        fragmentsAdapter.fragmentsItems = fragmentsItems

        viewPager.adapter = fragmentsAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = fragmentsItems[position].title
        }.attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    toolBar.hide()
                } else {
                    toolBar.show()
                }

            }
        })
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

}