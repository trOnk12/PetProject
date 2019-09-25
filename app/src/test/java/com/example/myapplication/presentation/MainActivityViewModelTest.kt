package com.example.myapplication.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.myapplication.domain.entity.Comment
import com.example.myapplication.domain.entity.Outcome
import com.example.myapplication.domain.usecase.CommentUseCase
import com.example.myapplication.presentation.main.MainActivityViewModel
import com.example.myapplication.successFullOutcome
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MainActivityViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val outComeObserver: Observer<Outcome<List<Comment>>> = mock()
    private val mockCommentUseCase: CommentUseCase = mock()

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @Captor
    private lateinit var argumentCaptor: ArgumentCaptor<Outcome<List<Comment>>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainActivityViewModel = MainActivityViewModel(mockCommentUseCase)
        mainActivityViewModel.commentListOutcome.observeForever(outComeObserver)
    }

    @Test
    fun testOutcomeSuccessful() {
        //Arrange
        val result =
            runBlocking {
                `when`(mockCommentUseCase.getComment())
                    .thenReturn(successFullOutcome)
            }
        //Act
        mainActivityViewModel.fetchComments()
        //Assert
        argumentCaptor.run {
            verify(outComeObserver, times(1)).onChanged(capture())
            assertEquals(result, value)
        }
    }


}