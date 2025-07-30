package com.todo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.common.util.ResourceState
import com.todo.core.domain.model.TodoEntity
import com.todo.core.domain.usecase.FetchTodoDetailUseCase
import com.todo.core.domain.usecase.FetchTodoListUseCase
import kotlinx.coroutines.flow.catch
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

    private val _selectedTodo = MutableLiveData<TodoEntity?>()
    val selectedTodo: LiveData<TodoEntity?> get() = _selectedTodo

    fun fetchTodoList() {
        _todoListState.value = ResourceState.Loading()
        viewModelScope.launch {
            fetchTodoListUseCase()
                .catch { throwable ->
                    _todoListState.postValue(
                        ResourceState.Error(
                            throwable.message ?: "Unknown error"
                        )
                    )
                }
                .collectLatest { result ->
                    _todoListState.postValue(result)
                }
        }
    }

    fun fetchTodoDetail(id: Int) {
        _todoDetailState.value = ResourceState.Loading()
        viewModelScope.launch {
            fetchTodoDetailUseCase(id)
                .catch { throwable ->
                    _todoDetailState.postValue(
                        ResourceState.Error(
                            throwable.message ?: "Unknown error"
                        )
                    )
                }
                .collectLatest { result ->
                    _todoDetailState.postValue(result)
                }
        }
    }

    fun toggleTodoCompleted(todo: TodoEntity, isChecked: Boolean) {
        val updatedTodo = todo.copy(completed = isChecked)
        val currentList =
            (_todoListState.value as? ResourceState.Success)?.data.orEmpty().toMutableList()
        val index = currentList.indexOfFirst { it.id == todo.id }
        if (index != -1) {
            currentList[index] = updatedTodo
            _todoListState.value = ResourceState.Success(currentList)
        }
    }

    fun selectTodo(todo: TodoEntity) {
        _selectedTodo.value = todo
    }

    fun clearSelectedTodo() {
        _selectedTodo.value = null
    }
}
