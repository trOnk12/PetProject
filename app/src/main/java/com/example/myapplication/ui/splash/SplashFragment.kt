package com.example.myapplication.ui.splash

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.CommentsFragmentBinding
import com.example.myapplication.ui.comment.CommentViewModel
import javax.inject.Inject

class SplashFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CommentViewModel
    private lateinit var binding: CommentsFragmentBinding


}