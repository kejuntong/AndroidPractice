package com.kejuntong.practiceproject.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kejuntong.practiceproject.databinding.FragmentTodoDetailsBinding
import com.kejuntong.practiceproject.viewmodel.TodoListViewModel

class TodoDetailsFragment : Fragment() {

    private var _binding: FragmentTodoDetailsBinding? = null
    // only valid between onCreate and onDestroy
    private val binding
        get() = _binding!!

    private lateinit var todoListViewModel: TodoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // this view model should be the same instance of view model that was created in
        // in FirstFragment
        todoListViewModel = ViewModelProvider(requireActivity())[TodoListViewModel::class.java]
        todoListViewModel.getSelectedItem().observe(viewLifecycleOwner) {
            binding.textTitle.text = it.title
            binding.textStatus.text = if(it.completed) "finished" else "TODO"
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}