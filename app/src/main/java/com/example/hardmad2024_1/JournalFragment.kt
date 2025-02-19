package com.example.hardmad2024_1

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hardmad2024_1.databinding.JournalFragmentBinding

class JournalFragment:Fragment(R.layout.journal_fragment){
    private lateinit var binding: JournalFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = JournalFragmentBinding.bind(view)
    }
}