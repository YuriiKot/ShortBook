package com.example.shortbooks.data.repository

import android.content.ContentResolver
import androidx.annotation.RawRes
import com.example.shortbooks.R
import com.example.shortbooks.data.mapper.BookDetailsMapper
import com.example.shortbooks.data.model.BookDetailsData
import com.example.shortbooks.data.model.KeyPointData
import com.example.shortbooks.domain.model.BookDetails
import com.example.shortbooks.domain.repository.BookDetailsRepository
import java.io.File

fun createUriFromRawFile(@RawRes res: Int): String {
    return (ContentResolver.SCHEME_ANDROID_RESOURCE
            + File.pathSeparator + File.separator + File.separator
            + "com.example.shortbooks"
            + File.separator
            + res)
}

val fakeData =
    BookDetailsData(
        "Antifragile",
        listOf(
            KeyPointData(
                "Embrace Volatility: The Power of Disorder",
                createUriFromRawFile(R.raw.antifragile_1)
            ),
            KeyPointData(
                "Optionality: Embracing Redundancy and Flexibility",
                createUriFromRawFile(R.raw.antifragile_2)
            ),
            KeyPointData(
                "Skin in the Game: Aligning Risk and Responsibility",
                createUriFromRawFile(R.raw.antifragile_3)
            ),
            KeyPointData(
                "Via Negativa: Subtraction over Addition",
                createUriFromRawFile(R.raw.antifragile_4)
            ),
        )
    )

class BookDetailsRepositoryImpl(
    private val bookDetailsMapper: BookDetailsMapper,
) : BookDetailsRepository {
    override suspend fun getBook(): BookDetails {
        return bookDetailsMapper.map(fakeData)
    }
}