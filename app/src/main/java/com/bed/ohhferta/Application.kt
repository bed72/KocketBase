package com.bed.ohhferta

import android.app.Application

import org.koin.core.logger.Level
import org.koin.core.context.startKoin

import org.koin.android.ext.koin.androidLogger
import org.koin.android.ext.koin.androidContext

import com.bed.ohhferta.framework.modules.clientsModule
import com.bed.ohhferta.framework.modules.viewModelsModule
import com.bed.ohhferta.framework.modules.datasourceModule
import com.bed.ohhferta.framework.modules.repositoriesModule


class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@Application)

            modules(
                clientsModule(),
                datasourceModule,
                repositoriesModule,
                viewModelsModule
            )
        }
    }
}
