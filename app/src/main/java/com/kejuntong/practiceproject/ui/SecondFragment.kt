package com.kejuntong.practiceproject.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kejuntong.practiceproject.databinding.FragmentSecondBinding
import com.kejuntong.practiceproject.viewmodel.FirstViewModel

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    // only valid between onCreate and onDestroy
    private val binding
        get() = _binding!!

    private lateinit var viewModel: FirstViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // this view model should be the same instance of view model that was created in
        // in FirstFragment
        viewModel = ViewModelProvider(requireActivity())[FirstViewModel::class.java]

        // testing here
        // since it's a same instance of view model, no need to call retrieve again
        viewModel.getTodoList().observe(viewLifecycleOwner) {
            Log.d("kejun test", "kejun test, observe test data in SecondFragment, $it")
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}