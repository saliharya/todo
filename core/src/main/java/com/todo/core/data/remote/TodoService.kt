package com.todo.core.data.remote

import com.todo.core.data.model.TodoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoService {

    @GET("todos")
    suspend fun getTodoList(): Response<List<TodoDto>>

    @GET("todos/{id}")
    suspend fun getTodoDetail(@Path("id") id: Int): Response<TodoDto>
}