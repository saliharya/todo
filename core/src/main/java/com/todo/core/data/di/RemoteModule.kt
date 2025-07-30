package com.todo.core.data.di

import com.todo.core.data.remote.TodoService
import com.todo.core.domain.usecase.FetchTodoDetailUseCase
import com.todo.core.domain.usecase.FetchTodoListUseCase
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.dsl.module

val remoteModule = module {
    single { Gson() }

    // UseCases
    single { FetchTodoListUseCase(get()) }
    single { FetchTodoDetailUseCase(get()) }

    // OkHttp Client
    single {
        OkHttpClient.Builder()
            .build()
    }

    // Retrofit
    single {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    // Service
    single {
        get<Retrofit>().create(TodoService::class.java)
    }
}
