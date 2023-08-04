package com.example.shortbooks.data.mapper

import com.example.shortbooks.data.model.BookDetailsData
import com.example.shortbooks.domain.model.BookDetails
import com.example.shortbooks.domain.model.KeyPoint

class BookDetailsMapper {
    fun map(param: BookDetailsData): BookDetails {
        return BookDetails(
            param.bookName,
            param.keyPoints.map {
                KeyPoint(
                    it.title,
                    it.playbackUrl,
                )
            }
        )
    }
}