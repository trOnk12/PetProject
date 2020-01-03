package com.example.myapplication.ui.dialog

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startForegroundService
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.core.extension.viewModel
import com.example.myapplication.data.firebase.FireBaseStorage
import com.example.myapplication.data.service.FireStorageService
import com.example.myapplication.ui.comment.CommentViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.option_dialog.view.*
import javax.inject.Inject

class ProfileImageChooserFragment : DialogFragment() {
    companion object {
        const val IMAGE_URI = "IMAGE_URI"
    }

    @Inject
    lateinit var provider: ViewModelProvider.Factory
    lateinit var commentViewModel: CommentViewModel

    private val FILE_PICKER = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.option_dialog, container)
        view.apply {
            file_source.setOnClickListener { chooseFile() }
            camera_source.setOnClickListener { openCamera() }
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        commentViewModel = viewModel(provider)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun chooseFile() {
        val chooseFileIntent = Intent(Intent.ACTION_GET_CONTENT)
        chooseFileIntent.type = "image/*"

        startActivityForResult(chooseFileIntent, FILE_PICKER)
    }

    private fun openCamera() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == FILE_PICKER) {
            data?.let {
                it.data.also { uri ->
                    activity?.let { activity ->
                        val intent = FireStorageService.callingIntent(activity)
                        intent.putExtra(IMAGE_URI, uri.toString())
                        activity.startService(intent)
                    }
                }
            }
        }
    }

}
