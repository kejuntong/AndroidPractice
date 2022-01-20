package com.kejuntong.practiceproject.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.kejuntong.practiceproject.R
import com.kejuntong.practiceproject.databinding.FragmentFirstBinding
import com.kejuntong.practiceproject.viewmodel.FirstViewModel

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    // only valid between onCreate and onDestroy
    private val binding
        get() = _binding!!

    private lateinit var viewModel: FirstViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // onActivityCreated is deprecate, so initialize / get the view model here
        // use requireActivity() to get Activity!! as lifecycle owner so that it retains
        // until activity finishes
        viewModel =  ViewModelProvider(requireActivity())[FirstViewModel::class.java]

        // testing here
        viewModel.retrieveTodoList()
        viewModel.getTodoList().observe(viewLifecycleOwner) {
            Log.d("kejun test", "kejun test, observe test data in FirstFragment, $it")
        }

        val testButton: Button = binding.testButton
        testButton.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.navigation_second)
        }
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