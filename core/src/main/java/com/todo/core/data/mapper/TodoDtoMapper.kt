package com.todo.core.data.mapper

import com.todo.core.data.model.TodoDto
import com.todo.core.domain.model.TodoEntity

fun TodoDto.toEntity(): TodoEntity {
    return TodoEntity(
        userId = userId,
        id = id,
        title = title,
        completed = completed
    )
}