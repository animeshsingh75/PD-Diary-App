package com.example.pddiary.fragments

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pddiary.Utils
import com.example.pddiary.adapter.PreviousDateAdapter
import com.example.pddiary.databinding.FragmentPreviousRecordsBinding
import java.io.File


class PreviousRecordsFragment : Fragment(), PreviousDateAdapter.OnItemClickListener {

    private lateinit var previousRecordBinding: FragmentPreviousRecordsBinding
    private val binding get() = previousRecordBinding

    private var startTime: Long = 0
    private lateinit var viewModel: DairyViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        previousRecordBinding = FragmentPreviousRecordsBinding.inflate(inflater, container, false)
        return previousRecordBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DairyViewModel::class.java]
        initObserver()
    }


    private fun initObserver() {
        viewModel.readData().observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.previousDateRv.visibility = View.GONE
                binding.emptyTv.visibility = View.VISIBLE
                return@observe
            }
            val adapter = PreviousDateAdapter(it, this)
            binding.previousDateRv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.previousDateRv.adapter = adapter
        }
    }

    override fun onItemClicked(date: String) {
        viewModel.buttonClicked("previous_dairy_screen", "view_diary_for_date_$date")
        val filePath = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            "$date.csv"
        ).path
        val diaryModelList = Utils.parseCsvToDairyModel(filePath)
        val action = HomeFragmentDirections.actionPreviousRecordsFragmentToDairyFragment(
            diaryModelList.toTypedArray(),
            date
        )
        findNavController().navigate(action)
    }


    override fun onStart() {
        startTime = System.currentTimeMillis()
        super.onStart()
    }

    override fun onStop() {
        val endTime = System.currentTimeMillis()
        val duration = ((endTime - startTime) / 1000).toInt()
        viewModel.insertScreenViewedDuration("previous_dairy_screen", duration)
        super.onStop()
    }
}