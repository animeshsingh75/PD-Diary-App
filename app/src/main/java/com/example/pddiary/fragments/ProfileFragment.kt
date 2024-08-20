package com.example.pddiary.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pddiary.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment() {

    private lateinit var profileBinding: ProfileFragmentBinding
    private val binding: ProfileFragmentBinding get() = profileBinding

    private var startTime: Long = 0

    private lateinit var viewModel: DairyViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileBinding = ProfileFragmentBinding.inflate(inflater, container, false)
        return profileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DairyViewModel::class.java]
        binding.submitBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Profile Updated", Toast.LENGTH_SHORT).show()
            viewModel.buttonClicked("profile_screen", "submit_button")
        }
    }

    override fun onStart() {
        startTime = System.currentTimeMillis()
        super.onStart()
    }

    override fun onStop() {
        val endTime = System.currentTimeMillis()
        val duration = ((endTime - startTime) / 1000).toInt()
        viewModel.insertScreenViewedDuration("profile_screen", duration)
        super.onStop()
    }
}