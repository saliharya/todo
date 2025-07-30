package com.todo.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.todo.core.domain.model.TodoEntity

class TodoDiffCallback(
    private val oldList: List<TodoEntity>,
    private val newList: List<TodoEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
