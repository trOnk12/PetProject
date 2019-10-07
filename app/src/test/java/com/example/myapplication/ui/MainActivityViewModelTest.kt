package com.example.myapplication.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.myapplication.domain.entity.Comment
import com.example.core.Outcome
import com.example.myapplication.domain.usecase.GetCommentsUseCase
import com.example.myapplication.ui.main.MainActivityViewModel
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

    private val outComeObserver: Observer<List<Comment>> = mock()
    private val mockGetCommentsUseCase: GetCommentsUseCase = mock()

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @Captor
    private lateinit var argumentCaptor: ArgumentCaptor<List<Comment>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainActivityViewModel = MainActivityViewModel(mockGetCommentsUseCase)
        mainActivityViewModel.commentList.observeForever(outComeObserver)
    }

    @Test
    fun testOutcomeSuccessful() {
        //Arrange
        val result =
            runBlocking {
                `when`(mockGetCommentsUseCase.getComment())
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