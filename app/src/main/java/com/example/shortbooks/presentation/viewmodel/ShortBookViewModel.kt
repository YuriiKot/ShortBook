package com.example.shortbooks.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shortbooks.playback.PlaybackStateFacade
import com.example.shortbooks.domain.usecase.GetBookDetails
import com.example.shortbooks.presentation.model.BookDetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

class ShortBookViewModel(
    private val getBookDetails: GetBookDetails,
    private val playbackStateFacade: PlaybackStateFacade,
) : ViewModel() {
    val currentStep = MutableStateFlow(0)

    private val bookDetails: StateFlow<BookDetailsState> = flow {
        emit(BookDetailsState.Success(getBookDetails.execute()))
    }
        .onEach { currentStep.emit(0) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, BookDetailsState.Initial)

    val totalSteps = bookDetails
        .map {
            it.totalSteps()
        }.stateIn(viewModelScope, SharingStarted.Eagerly, 0)

    val currentKeyPointDescription = combine(
        currentStep,
        bookDetails.filterIsInstance<BookDetailsState.Success>()
    ) { step, bookDetails ->
        bookDetails.bookDetails.keyPoints[step].title
    }
        .stateIn(viewModelScope, SharingStarted.Eagerly, "")

    init {
        combine(
            currentStep,
            bookDetails.filterIsInstance<BookDetailsState.Success>()
        ) { step, bookDetails ->
            val keyPointUrl = bookDetails.bookDetails.keyPoints[step].playbackUrl
            playbackStateFacade.setPlayback(keyPointUrl)
        }
            .launchIn(viewModelScope)

        playbackStateFacade.playEnded
            .filter { ended -> ended }
            .onEach {
                skipForward()
            }
            .launchIn(viewModelScope)
    }

    val canSkipForward = currentStep
        .map { step -> bookDetails.value.canSkipForward(step) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val canSkipBack = currentStep
        .map { step -> bookDetails.value.canSkipBack(step) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun skipForward() {
        if (bookDetails.value.canSkipForward(currentStep.value)) {
            currentStep.value = currentStep.value + 1
        }
    }

    fun skipBack() {
        if (bookDetails.value.canSkipBack(currentStep.value)) {
            currentStep.value = currentStep.value - 1
        }
    }
}
