package com.todo.presentation.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.todo.common.base.BaseFragment
import com.todo.common.util.showToast
import com.todo.presentation.R
import com.todo.presentation.activity.DetailActivity
import com.todo.presentation.adapter.TodoListAdapter
import com.todo.presentation.databinding.FragmentTodoListBinding
import com.todo.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TodoListFragment : BaseFragment<FragmentTodoListBinding, MainViewModel>() {

    override val viewModel: MainViewModel by sharedViewModel()
    private lateinit var adapter: TodoListAdapter

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentTodoListBinding.inflate(inflater, container, false)

    override fun setupViews() {
        adapter = TodoListAdapter(
            listTodo = emptyList()
        ) { todo ->
            if (requireActivity().findViewById<View?>(R.id.fragmentDetailContainer) != null) {
                val fragment = TodoDetailFragment.newInstance(todo.id)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentDetailContainer, fragment)
                    .commit()
            } else {
                // Phone mode: buka DetailActivity
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("TODO_ID", todo.id)
                startActivity(intent)
            }
        }

        binding.rvTodoList.adapter = adapter
        viewModel.fetchTodoList()
    }

    override fun observeData() {
        viewModel.todoListLiveData.observe(viewLifecycleOwner) { todos ->
            adapter.submitList(todos)
            binding.tvEmpty.isVisible = todos.isEmpty()
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { msg ->
            requireContext().showToast(msg)
        }
    }
}
