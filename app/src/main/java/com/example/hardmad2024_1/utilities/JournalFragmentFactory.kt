package com.example.hardmad2024_1.utilities

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.hardmad2024_1.JournalFragment

class JournalFragmentFactory(private val data:Array<JournalFragmentData>):FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            JournalFragmentData::class.java.name -> JournalFragment.createNewInstance(data)
            else -> super.instantiate(classLoader, className)
        }
    }
}