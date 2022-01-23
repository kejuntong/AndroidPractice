package com.kejuntong.practiceproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kejuntong.practiceproject.R
import com.kejuntong.practiceproject.api.TodoItem
import com.kejuntong.practiceproject.databinding.FragmentTodoListBinding
import com.kejuntong.practiceproject.viewmodel.TodoListViewModel


class TodoListFragment : Fragment() {

    private var _binding: FragmentTodoListBinding? = null
    // only valid between onCreate and onDestroy
    private val binding
        get() = _binding!!

    private lateinit var viewModel: TodoListViewModel
    private lateinit var adapter: TodoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // onActivityCreated is deprecate, so initialize / get the view model here
        // use requireActivity() to get Activity!! as lifecycle owner so that it retains
        // until activity finishes
        viewModel =  ViewModelProvider(requireActivity())[TodoListViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = TodoListAdapter(listOf())
        adapter.setOnItemClickListener(object : TodoListAdapter.OnItemClickListener {
            override fun onItemClicked(todoItem: TodoItem) {
                viewModel.selectItem(todoItem)
                Navigation.findNavController(binding.root).navigate(R.id.navigation_todo_details)
            }
        })
        binding.recyclerView.adapter = adapter

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        viewModel.getTodoList().observe(viewLifecycleOwner) {
            adapter.refresh(it)
        }

        viewModel.retrieveTodoList()
    }

    /*
     * It is a valid concern in fragments because the fragment instance can live
     * much longer than its view. For example, when the fragment instance is on
     * the back stack with its view already destroyed.
     */
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}