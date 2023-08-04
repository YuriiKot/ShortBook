package com.example.shortbooks.presentation.model

import com.example.shortbooks.domain.model.BookDetails

sealed class BookDetailsState {
    object Initial : BookDetailsState() {
        override fun canSkipForward(step: Int) = false

        override fun canSkipBack(step: Int): Boolean = false

        override fun totalSteps(): Int = 0
    }

    class Success(val bookDetails: BookDetails) : BookDetailsState() {
        override fun canSkipForward(step: Int): Boolean = step < bookDetails.keyPoints.size - 1

        override fun canSkipBack(step: Int): Boolean = step > 0

        override fun totalSteps() = bookDetails.getTotalSteps()
    }

    abstract fun canSkipForward(step: Int): Boolean

    abstract fun canSkipBack(step: Int): Boolean

    abstract fun totalSteps(): Int
}