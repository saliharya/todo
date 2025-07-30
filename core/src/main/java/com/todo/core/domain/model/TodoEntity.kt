package com.todo.core.domain.model

data class TodoEntity(
    val id: Int,
    val userId: Int,
    val title: String,
    val completed: Boolean
)