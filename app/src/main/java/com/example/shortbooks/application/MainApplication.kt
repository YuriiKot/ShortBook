package com.example.shortbooks.application

import android.app.Application
import com.example.shortbooks.di.appModule
import com.example.shortbooks.di.bookDetailsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class ShortBookApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ShortBookApplication)
            modules(
                appModule,
                bookDetailsModule,
            )
        }
    }
}