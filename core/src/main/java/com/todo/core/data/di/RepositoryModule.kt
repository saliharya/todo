package com.todo.core.data.di

import com.todo.core.data.repository.TodoRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<TodoRepositoryImpl> { TodoRepositoryImpl(get()) }
}