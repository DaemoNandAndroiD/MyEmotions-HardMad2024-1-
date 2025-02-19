package com.example.hardmad2024_1

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.hardmad2024_1.databinding.CoordinatorBinding

class MainActivity:FragmentActivity() {
    private lateinit var binding:CoordinatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = CoordinatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavBar = binding.navBar
        bottomNavBar.setupWithNavController(navController)

        bottomNavBar.setOnItemSelectedListener { item ->
            val navOptions = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setRestoreState(true)
                .build()

            when (item.itemId) {
                R.id.nav_journal -> navController.navigate(R.id.nav_journal, null, navOptions)
            }
            true
        }
    }
}