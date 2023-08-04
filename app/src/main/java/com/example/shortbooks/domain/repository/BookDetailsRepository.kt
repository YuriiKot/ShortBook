package com.example.shortbooks.domain.repository

import com.example.shortbooks.domain.model.BookDetails

interface BookDetailsRepository {
    suspend fun getBook(): BookDetails
}
