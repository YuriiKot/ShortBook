package com.example.shortbooks.domain.usecase

import com.example.shortbooks.domain.model.BookDetails
import com.example.shortbooks.domain.repository.BookDetailsRepository

class GetBookDetails(
    private val getBookDetailsRepository: BookDetailsRepository,
) {
    suspend fun execute(): BookDetails {
        return getBookDetailsRepository.getBook()
    }
}