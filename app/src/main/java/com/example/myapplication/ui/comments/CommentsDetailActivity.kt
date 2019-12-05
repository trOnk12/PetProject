package com.example.myapplication.ui.comments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.domain.model.Comment

class CommentsDetailActivity : AppCompatActivity() {
    companion object {
        private const val INTENT_EXTRA_PARAM_COMMENT = "INTENT_PARAM_COMMENT"

        fun callingIntent(context: Context, comment: Comment): Intent {
            val intent = Intent(context, CommentsDetailActivity::class.java)
           // intent.putExtra(INTENT_EXTRA_PARAM_COMMENT, comment)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comments_detail_activity)
    }

}