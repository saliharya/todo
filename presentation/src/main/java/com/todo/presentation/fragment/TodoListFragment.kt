package com.todo.presentation.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.todo.common.base.BaseFragment
import com.todo.presentation.R
import com.todo.presentation.activity.DetailActivity
import com.todo.presentation.adapter.TodoListAdapter
import com.todo.presentation.databinding.FragmentTodoListBinding
import com.todo.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TodoListFragment : BaseFragment<FragmentTodoListBinding, MainViewModel>() {

    override val viewModel: MainViewModel by sharedViewModel()

    private lateinit var adapter: TodoListAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTodoListBinding = FragmentTodoListBinding.inflate(inflater, container, false)

    override fun setupViews() {
        adapter = TodoListAdapter(emptyList()) { selectedTodo ->
            val isTablet = binding.root.findViewById<View?>(R.id.fragmentDetailContainer) != null

            if (isTablet) {
                viewModel.selectTodo(selectedTodo)
            } else {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("todo", selectedTodo)
                startActivity(intent)
            }
        }

        binding.rvTodoList.adapter = adapter
    }

    override fun observeData() {
        viewModel.todoList.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }
    }
}
