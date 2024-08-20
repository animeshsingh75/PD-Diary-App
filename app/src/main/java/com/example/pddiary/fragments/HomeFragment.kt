package com.example.pddiary.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pddiary.R
import com.example.pddiary.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private lateinit var homeBinding: HomeFragmentBinding
    private val binding get() = homeBinding

    private lateinit var viewModel: DairyViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = HomeFragmentBinding.inflate(inflater, container, false)
        return homeBinding.root
    }

    private var startTime: Long = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DairyViewModel::class.java]
        binding.newDiaryCard.setOnClickListener {
            viewModel.resetDairyList()
            viewModel.buttonClicked("home_screen", "new_diary")
            findNavController().navigate(R.id.action_global_dairyFragment)
        }

        binding.viewUpdateDiaryCard.setOnClickListener {
            viewModel.buttonClicked("home_screen", "update_dairy")
            findNavController().navigate(R.id.action_global_previousFragment)
        }
    }

    override fun onStart() {
        startTime = System.currentTimeMillis()
        super.onStart()
    }

    override fun onStop() {
        val endTime = System.currentTimeMillis()
        val duration = ((endTime - startTime) / 1000).toInt()
        viewModel.insertScreenViewedDuration("home_screen", duration)
        super.onStop()
    }
}