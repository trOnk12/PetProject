package com.example.myapplication.ui.comments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.domain.model.Comment
import com.example.core_ui.platform.BaseActivity
import com.example.core_ui.platform.BaseFragment
import com.example.myapplication.di.injectFeature

class CommentsActivity : BaseActivity(){
    companion object {
        fun callingIntent(context: Context): Intent = Intent(context, CommentsActivity::class.java)
    }

    override fun fragment(): BaseFragment = CommentsFragment()
}
