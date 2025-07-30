package com.todo.presentation.activity

import com.todo.common.base.BaseActivity
import com.todo.presentation.R
import com.todo.presentation.databinding.ActivityDetailBinding
import com.todo.presentation.fragment.TodoDetailFragment
import com.todo.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity<ActivityDetailBinding, MainViewModel>() {

    override val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override val viewModel: MainViewModel by viewModel()

    override fun setupViews() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.detail_fragment_container, TodoDetailFragment())
            .commit()
    }

    override fun observeData() {
    }
}
