package com.example.hardmad2024_1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hardmad2024_1.databinding.SettingsFragmentBinding

class SettingsFragment: Fragment(R.layout.settings_fragment) {
    private lateinit var binding: SettingsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SettingsFragmentBinding.bind(view)
    }
}