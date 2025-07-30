package com.todo.core.domain.usecase

import com.todo.common.util.ResourceState
import com.todo.core.domain.model.TodoEntity
import com.todo.core.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class FetchTodoListUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(): Flow<ResourceState<List<TodoEntity>>> {
        return repository.fetchTodoList()
    }
}
