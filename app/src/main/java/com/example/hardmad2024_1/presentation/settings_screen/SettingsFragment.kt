package com.example.hardmad2024_1.presentation.settings_screen

import android.Manifest
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.compose.runtime.collectAsState
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.databinding.SettingsFragmentBinding
import com.example.hardmad2024_1.domain.util.StateHandler
import com.example.hardmad2024_1.presentation.services.ReminderManager
import com.example.hardmad2024_1.presentation.settings_screen.component.NotificationRecyclerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalTime

@AndroidEntryPoint
class SettingsFragment: Fragment(R.layout.settings_fragment) {
    private lateinit var binding: SettingsFragmentBinding
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private val viewModel by viewModels<SettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (!isGranted) {
                    Toast.makeText(requireContext(), "Вы можете включить отправку уведомлений позднее", Toast.LENGTH_SHORT).show()
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SettingsFragmentBinding.bind(view)

        observeViewModel()
        setupRecyclerView()
        setupClickListeners()
    }

    private val adapter = NotificationRecyclerAdapter()

    private fun setupRecyclerView() {
        binding.notificationsContainer.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@SettingsFragment.adapter
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.userState.collect { state ->
                    when(state){
                        is StateHandler.Error -> Toast.makeText(requireContext(), "Не удалось получить данные", Toast.LENGTH_SHORT).show()
                        StateHandler.Initial -> Unit
                        StateHandler.Loading -> Unit
                        is StateHandler.Success -> {
                            binding.name.text = state.data.name
                        }
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun setupClickListeners() {
        binding.addNotification.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }

            val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
            if (!alarmManager.canScheduleExactAlarms()) {
                startActivity(Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM))
            }

            showBottomSheetWithPicker()
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun showBottomSheetWithPicker() {
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(CLOCK_24H)
            .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
            .setTheme(R.style.BaseTheme_TimePicker)
            .build()

        val bottomSheet = BottomSheetDialog(requireContext())
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_content, null)
        val hoursText = view.findViewById<TextView>(R.id.hours)
        val minutesText = view.findViewById<TextView>(R.id.minutes)

        val currentTime = LocalTime.now()
        hoursText.text = String.format("%02d", currentTime.hour)
        minutesText.text = String.format("%02d", currentTime.minute)

        view.findViewById<LinearLayout>(R.id.time_section).setOnClickListener {
            timePicker.show(parentFragmentManager, "timePicker")
        }

        view.findViewById<AppCompatButton>(R.id.saveBtn).setOnClickListener {
            val timeText = "${hoursText.text}:${minutesText.text}"
            adapter.addItem(timeText)

            ReminderManager.scheduleReminder(requireContext(), timePicker.hour, timePicker.minute)
            bottomSheet.dismiss()
        }

        timePicker.addOnPositiveButtonClickListener {
            hoursText.text = String.format("%02d", timePicker.hour)
            minutesText.text = String.format("%02d", timePicker.minute)
        }

        bottomSheet.setContentView(view)
        bottomSheet.show()
    }
}