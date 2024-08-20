package com.example.pddiary

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.pddiary.databinding.MainActivityBinding
import com.example.pddiary.fragments.DairyViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var viewModel: DairyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this)[DairyViewModel::class.java]
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }

        val navController = findNavController(R.id.fragment_container)

        setSelectedState(home = true)

        binding.home.setOnClickListener {
            navController.navigate(R.id.action_global_homeFragment)
            setSelectedState(home = true)
        }

        binding.dairy.setOnClickListener {
            navController.navigate(R.id.action_global_dairyFragment)
            viewModel.resetDairyList()
            setSelectedState(dairy = true)
        }

        binding.profile.setOnClickListener {
            navController.navigate(R.id.action_global_profileFragment)
            setSelectedState(profile = true)
        }
    }

    private fun setSelectedState(home: Boolean = false, dairy: Boolean = false, profile: Boolean = false) {
        binding.home.isSelected = home
        binding.dairy.isSelected = dairy
        binding.profile.isSelected = profile
    }
}