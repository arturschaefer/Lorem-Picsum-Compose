package com.schaefer.lorempicsum

import android.app.Application
import com.schaefer.lorempicsum.core.di.networkModule
import com.schaefer.lorempicsum.imagelist.di.imageListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

internal class LoremPicsumApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(androidContext = this@LoremPicsumApp)

            modules(
                listOf(
                    networkModule,
                    imageListModule,
                )
            )
        }
    }
}
