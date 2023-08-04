package com.example.shortbooks.di

import com.example.shortbooks.data.mapper.BookDetailsMapper
import com.example.shortbooks.data.repository.BookDetailsRepositoryImpl
import com.example.shortbooks.domain.repository.BookDetailsRepository
import com.example.shortbooks.domain.usecase.GetBookDetails
import com.example.shortbooks.presentation.viewmodel.ShortBookViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val bookDetailsModule = module {
    viewModelOf(::ShortBookViewModel)
    singleOf(::GetBookDetails)
    singleOf(::BookDetailsMapper)
    singleOf(::BookDetailsRepositoryImpl) { bind<BookDetailsRepository>() }
}