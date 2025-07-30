package com.todo.todo

import androidx.multidex.MultiDex
import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.todo.core.data.di.remoteModule
import com.todo.core.data.di.repositoryModule
import com.todo.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TodoApp : SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        startKoin {
            androidContext(this@TodoApp)
            modules(
                listOf(
                    remoteModule,
                    presentationModule,
                    repositoryModule,
                )
            )
        }
    }
}