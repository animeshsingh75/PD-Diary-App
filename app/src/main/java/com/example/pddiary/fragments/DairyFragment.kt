package com.example.pddiary.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pddiary.Utils.saveDataToCSV
import com.example.pddiary.adapter.DairyAdapter
import com.example.pddiary.databinding.DairyFragmentBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DairyFragment : Fragment() {

    private lateinit var dairyBinding: DairyFragmentBinding
    private val binding: DairyFragmentBinding get() = dairyBinding
    private lateinit var calendarDate: String;
    private lateinit var viewModel: DairyViewModel

    private var startTime: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dairyBinding = DairyFragmentBinding.inflate(inflater, container, false)
        return dairyBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DairyViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dairyList = arguments?.let { DairyFragmentArgs.fromBundle(it).dairyList?.toList() }
        val date = arguments?.let { DairyFragmentArgs.fromBundle(it).date }
        val initialDate = Calendar.getInstance()
        val calendar = initialDate.time
        if (dairyList != null) {
            viewModel.setDairyList(dairyList)
            binding.dateTv.text = date
            binding.imageView.isEnabled = false
        } else {
            val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            calendarDate = sdf.format(calendar)
            binding.dateTv.text = calendarDate
        }
        val list = viewModel.getDairyList()
        binding.dairyRecyclerview.layoutManager = LinearLayoutManager(context)
        val adapter = DairyAdapter(list) {
            val headers = listOf(
                "Time",
                "Asleep",
                "On",
                "onWithTroublesome",
                "onWithoutTroublesome",
                "Off",
                "Med1Status",
                "Med2Status",
                "Measurement"
            )
            viewModel.buttonClicked("dairy_screen", "submit_dairy")
            if (date != null) {
                val file = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "$date.csv"
                )
                saveDataToCSV(headers, list, file)
                viewModel.queryIncrementOrInsertWithTimestamp(date)
                viewModel.queryIncrement()
                Toast.makeText(requireContext(), "Your data has been recorded", Toast.LENGTH_SHORT)
                    .show()
                parentFragmentManager.popBackStack()
                return@DairyAdapter
            } else {
                val file = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "$calendarDate.csv"
                )
                if (file.exists()) {
                    Toast.makeText(
                        requireContext(),
                        "You have entered the data for this data please edit to change it",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Your data has been recorded",
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.queryIncrementOrInsertWithTimestamp(calendarDate)
                    viewModel.queryIncrement()
                    parentFragmentManager.popBackStack()
                    saveDataToCSV(headers, list, file)
                    viewModel.saveData(calendarDate)
                }
            }
        }
        binding.dairyRecyclerview.adapter = adapter

        binding.imageView.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val dateToSet = Calendar.getInstance()
                    dateToSet.set(Calendar.YEAR, year)
                    dateToSet.set(Calendar.MONTH, month)
                    dateToSet.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val simpleDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                    initialDate.set(year, month, dayOfMonth)
                    val formattedDate = simpleDateFormat.format(dateToSet.time)
                    calendarDate = formattedDate
                    binding.dateTv.text = calendarDate
                },
                initialDate.get(Calendar.YEAR),
                initialDate.get(Calendar.MONTH),
                initialDate.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()

        }
    }

    override fun onStart() {
        startTime = System.currentTimeMillis()
        super.onStart()
    }

    override fun onStop() {
        val endTime = System.currentTimeMillis()
        val duration = ((endTime - startTime) / 1000).toInt()
        viewModel.insertScreenViewedDuration("dairy_screen", duration)
        super.onStop()
    }

}