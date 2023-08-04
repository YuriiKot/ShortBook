package com.example.shortbooks.di

import com.example.shortbooks.playback.PlaybackStateFacade
import com.example.shortbooks.playback.PlaybackStateFacadeImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::PlaybackStateFacadeImpl) { bind<PlaybackStateFacade>() }
}

