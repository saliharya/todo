package com.todo.presentation.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.todo.common.base.BaseFragment
import com.todo.common.util.ResourceState
import com.todo.common.util.showToast
import com.todo.presentation.R
import com.todo.presentation.activity.DetailActivity
import com.todo.presentation.adapter.TodoListAdapter
import com.todo.presentation.databinding.FragmentTodoListBinding
import com.todo.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TodoListFragment : BaseFragment<FragmentTodoListBinding, MainViewModel>() {

    override val viewModel: MainViewModel by sharedViewModel()

    private lateinit var todoAdapter: TodoListAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTodoListBinding = FragmentTodoListBinding.inflate(inflater, container, false)

    override fun setupViews() {
        todoAdapter = TodoListAdapter(
            listTodo = emptyList(),
            onItemClicked = { selectedTodo ->
                val isTablet =
                    binding.root.findViewById<View?>(R.id.detail_fragment_container) != null
                if (isTablet) {
                    viewModel.selectTodo(selectedTodo)
                } else {
                    viewModel.selectTodo(selectedTodo)
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra("todo", selectedTodo.id)
                    startActivity(intent)
                }
            },
            onTodoCheckedChanged = { todo, isChecked ->
                viewModel.toggleTodoCompleted(todo, isChecked)
            }
        )

        with(binding.rvTodoList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = todoAdapter
        }
    }

    override fun observeData() {
        viewModel.fetchTodoList()
        viewModel.todoListState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResourceState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvTodoList.visibility = View.GONE
                    binding.tvEmptyState.visibility = View.GONE
                }

                is ResourceState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val todos = state.data.orEmpty()
                    todoAdapter.updateData(todos)

                    binding.rvTodoList.visibility =
                        if (todos.isNotEmpty()) View.VISIBLE else View.GONE
                    binding.tvEmptyState.visibility =
                        if (todos.isEmpty()) View.VISIBLE else View.GONE
                }

                is ResourceState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvTodoList.visibility = View.GONE
                    binding.tvEmptyState.visibility = View.VISIBLE
                    requireActivity().showToast(state.message)
                }
            }
        }
    }
}