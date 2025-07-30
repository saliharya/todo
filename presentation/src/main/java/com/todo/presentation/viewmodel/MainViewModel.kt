package com.todo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.common.util.ResourceState
import com.todo.core.domain.model.TodoEntity
import com.todo.core.domain.usecase.FetchTodoDetailUseCase
import com.todo.core.domain.usecase.FetchTodoListUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val fetchTodoListUseCase: FetchTodoListUseCase,
    private val fetchTodoDetailUseCase: FetchTodoDetailUseCase
) : ViewModel() {

    private val _todoListState = MutableLiveData<ResourceState<List<TodoEntity>>>()
    val todoListState: LiveData<ResourceState<List<TodoEntity>>> get() = _todoListState

    private val _todoDetailState = MutableLiveData<ResourceState<TodoEntity>>()
    val todoDetailState: LiveData<ResourceState<TodoEntity>> get() = _todoDetailState

    fun fetchTodoList() {
        viewModelScope.launch {
            fetchTodoListUseCase().collectLatest {
                _todoListState.postValue(it)
            }
        }
    }

    fun fetchTodoDetail(id: Int) {
        viewModelScope.launch {
            fetchTodoDetailUseCase(id).collectLatest {
                _todoDetailState.postValue(it)
            }
        }
    }
}
