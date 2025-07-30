package com.todo.core.domain.repository

import com.todo.common.util.ResourceState
import com.todo.core.domain.model.TodoEntity
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun fetchTodoList(): Flow<ResourceState<List<TodoEntity>>>
    suspend fun fetchTodoDetail(id: Int): Flow<ResourceState<TodoEntity>>
}