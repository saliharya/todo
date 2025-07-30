package com.todo.presentation.activity

import com.todo.common.base.BaseActivity
import com.todo.presentation.R
import com.todo.presentation.databinding.ActivityMainBinding
import com.todo.presentation.fragment.TodoDetailFragment
import com.todo.presentation.fragment.TodoListFragment
import com.todo.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override val viewModel: MainViewModel by viewModel()

    override fun setupViews() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentListContainer, TodoListFragment())
            .commit()

        if (binding.fragmentDetailContainer != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentDetailContainer!!, TodoDetailFragment())
                .commit()
        }
    }

    override fun observeData() {}
}