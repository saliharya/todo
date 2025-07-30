package com.todo.core.data.di

import com.todo.core.data.repository.TodoRepositoryImpl
import com.todo.core.domain.repository.TodoRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<TodoRepository> { TodoRepositoryImpl(get()) }
}