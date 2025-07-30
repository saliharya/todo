package com.todo.presentation.activity


import android.content.Intent
import androidx.core.view.isVisible
import com.todo.common.base.BaseActivity
import com.todo.common.util.showToast
import com.todo.presentation.adapter.TodoListAdapter
import com.todo.presentation.databinding.ActivityMainBinding
import com.todo.presentation.fragment.TodoDetailFragment
import com.todo.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override val viewModel by viewModel<MainViewModel>()

    private var isTablet = false
    private lateinit var adapter: TodoListAdapter

    override fun setupViews() {
        isTablet = binding.fragmentDetailContainer != null

        adapter = TodoListAdapter() { todo ->
            if (isTablet) {
                val fragment = TodoDetailFragment.newInstance(todo.id)
                supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentDetailContainer!!.id, fragment)
                    .commit()
            } else {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("TODO_ID", todo.id)
                startActivity(intent)
            }
        }

        binding.rvTodoList.adapter = adapter

        viewModel.fetchTodoList()
    }

    override fun observeData() {
        viewModel.todoListLiveData.observe(this) { todos ->
            adapter.submitList(todos)
            binding.tvEmpty.isVisible = todos.isEmpty()
        }

        viewModel.errorMessage.observe(this) { message ->
            showToast(message)
        }
    }
}