package com.todo.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.todo.common.base.BaseFragment
import com.todo.presentation.databinding.FragmentTodoDetailBinding
import com.todo.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TodoDetailFragment : BaseFragment<FragmentTodoDetailBinding, MainViewModel>() {

    override val viewModel: MainViewModel by sharedViewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTodoDetailBinding = FragmentTodoDetailBinding.inflate(inflater, container, false)

    override fun setupViews() {}

    override fun observeData() {
        viewModel.selectedTodo.observe(viewLifecycleOwner) { todo ->
            binding.tvTitle.text = todo?.title
            binding.cbComplete.isChecked = todo?.completed == true
        }
    }
}
