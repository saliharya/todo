package com.todo.core.data.repository

import com.todo.common.util.ResourceState
import com.todo.common.util.tryParse
import com.todo.core.data.mapper.toEntity
import com.todo.core.data.remote.TodoService
import com.todo.core.domain.repository.TodoRepository
import kotlinx.coroutines.flow.flow

class TodoRepositoryImpl(
    private val todoService: TodoService
) : TodoRepository {

    override suspend fun fetchTodoList() = flow {
        emit(ResourceState.Loading())
        val response = todoService.getTodoList()
        emit(response.tryParse {
            it?.map { dto -> dto.toEntity() }.orEmpty()
        })
    }

    override suspend fun fetchTodoDetail(id: Int) = flow {
        emit(ResourceState.Loading())
        val response = todoService.getTodoDetail(id)
        emit(response.tryParse {
            it?.toEntity()
        })
    }
}