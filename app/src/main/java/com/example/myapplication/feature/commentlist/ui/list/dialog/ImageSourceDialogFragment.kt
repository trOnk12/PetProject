package com.example.myapplication.feature.commentlist.ui.list.dialog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.core.extensions.viewModel
import com.example.myapplication.data.service.FireStorageService
import com.example.myapplication.feature.commentlist.ui.list.CommentsListViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import kotlinx.android.synthetic.main.option_dialog.view.*

class ImageSourceDialogFragment : DialogFragment() {
    companion object {
        const val IMAGE_URI_EXTRA = "IMAGE_URI_EXTRA"
        const val FILE_PICKER_RESULT_CODE = 1
    }

    @Inject
    lateinit var provider: ViewModelProvider.Factory
    lateinit var viewModel: CommentsListViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.example.myapplication.R.layout.option_dialog, container).also { view ->
            view.fileSource.setOnClickListener { chooseFile() }
            view.cameraSource.setOnClickListener { openCamera() }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = viewModel(provider)
    }

    private fun chooseFile() {
        Intent(Intent.ACTION_GET_CONTENT).also { intent ->
            intent.type = "image/*"
            startActivityForResult(intent,
                FILE_PICKER_RESULT_CODE
            )
        }
    }

    private fun openCamera() {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == FILE_PICKER_RESULT_CODE) {
            data?.data?.let { uri ->
                activity?.let { activity ->
                    val intent = FireStorageService.callingIntent(activity)
                    intent.putExtra(IMAGE_URI_EXTRA, uri.toString())
                    activity.startService(intent)
                }
            }
        }
    }
}
