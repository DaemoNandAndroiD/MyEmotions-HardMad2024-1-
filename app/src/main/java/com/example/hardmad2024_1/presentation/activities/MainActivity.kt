package com.example.hardmad2024_1.presentation.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.hardmad2024_1.presentation.fragments.JournalFragment
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.databinding.MainActivityBinding
import com.example.hardmad2024_1.presentation.util.classes.JournalFragmentData

class MainActivity:FragmentActivity() {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.main
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavBar = binding.navBar
        bottomNavBar.setupWithNavController(navController)

        val journalData = arrayOf(
            JournalFragmentData("сегодня, 12:00",
                "выгорание",
                R.color.blue_text,
                R.drawable.card_shape_blue,
                R.drawable.ic_blue_emote
            ),
            JournalFragmentData("сегодня, 10:45",
                "спокойствие",
                R.color.green_text,
                R.drawable.card_shape_green,
                R.drawable.ic_green_emote
            ),
            JournalFragmentData("вчера, 23:11",
                "продуктивность",
                R.color.yellow_text,
                R.drawable.card_shape_yellow,
                R.drawable.ic_yellow_emote
            ),
            JournalFragmentData("вчера, 11:11",
                "выгорание",
                R.color.red_text,
                R.drawable.card_shape_red,
                R.drawable.ic_red_emote
            )
        )

        val navOptions = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setRestoreState(true)
            .build()

        bottomNavBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_journal -> {
                    navController.navigate(R.id.nav_journal, Bundle().apply {
                        putParcelableArray(JournalFragment.BUNDLE_KEY, journalData)
                    }, navOptions)
                }
                R.id.nav_statistics -> navController.navigate(R.id.nav_statistics, null, navOptions)
                R.id.nav_settings -> navController.navigate(R.id.nav_settings, null, navOptions)
            }
            true
        }

        navController.navigate(R.id.nav_journal, Bundle().apply {
            putParcelableArray(JournalFragment.BUNDLE_KEY, journalData)
        }, navOptions)
    }
}