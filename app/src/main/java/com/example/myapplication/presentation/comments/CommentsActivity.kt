package com.example.myapplication.presentation.comments

import android.content.Context
import android.content.Intent
import com.example.core_ui.platform.BaseActivity
import com.example.core_ui.platform.BaseFragment

class CommentsActivity : BaseActivity(){
    companion object {
        fun callingIntent(context: Context): Intent = Intent(context, CommentsActivity::class.java)
    }

    override fun fragment(): BaseFragment = CommentsFragment()

}
