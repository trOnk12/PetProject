package com.example.myapplication.ui.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.core.extension.viewModel
import com.example.myapplication.ui.comment.CommentViewModel
import kotlinx.android.synthetic.main.option_dialog.view.*
import javax.inject.Inject

class ProfileImageChooserFragment : DialogFragment() {

    @Inject
    lateinit var provider: ViewModelProvider.Factory
    lateinit var commentViewModel: CommentViewModel

    private val FILE_PICKER = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(com.example.myapplication.R.layout.option_dialog, container)
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
                val chosenImageUri = it.data
                commentViewModel.uploadProfileImage(chosenImageUri)
            }
        }
    }

}
