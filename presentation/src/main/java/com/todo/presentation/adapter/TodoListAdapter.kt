package com.todo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.todo.core.domain.model.TodoEntity
import com.todo.presentation.databinding.ItemTodoBinding

class TodoListAdapter(
    private var listTodo: List<TodoEntity>,
    private val onItemClicked: (TodoEntity) -> Unit
) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TodoViewHolder(binding)
    }

    override fun getItemCount() = listTodo.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = listTodo.getOrNull(position)
        holder.bind(todo)
    }

    fun updateData(newList: List<TodoEntity>) {
        val diffCallback = TodoDiffCallback(listTodo, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        listTodo = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class TodoViewHolder(
        private val binding: ItemTodoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: TodoEntity?) = with(binding) {
            todo?.let {
                root.setOnClickListener { onItemClicked(todo) }
                tvTitle.text = todo.title
            }
        }
    }
}