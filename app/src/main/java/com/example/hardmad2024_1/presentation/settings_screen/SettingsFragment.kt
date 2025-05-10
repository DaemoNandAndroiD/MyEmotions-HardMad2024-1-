package com.example.hardmad2024_1.presentation.settings_screen

import android.Manifest
import android.app.Activity
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.databinding.SettingsFragmentBinding
import com.example.hardmad2024_1.domain.util.StateHandler
import com.example.hardmad2024_1.presentation.components.notification.ReminderManager
import com.example.hardmad2024_1.presentation.settings_screen.component.NotificationRecyclerAdapter
import com.example.hardmad2024_1.presentation.welcome_screen.WelcomeActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.time.LocalTime
import java.util.UUID


@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.settings_fragment) {
    private lateinit var binding: SettingsFragmentBinding
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
    private val viewModel by viewModels<SettingsViewModel>()
    private val adapterNotifications = NotificationRecyclerAdapter(onDelete = {
        viewModel.deleteNotification(it)
    })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (!isGranted) {
                    Toast.makeText(
                        requireContext(),
                        "Вы можете включить отправку уведомлений позднее",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        imagePickerLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data
                uri?.let {
                    saveImageToInternalStorage(it)
                }
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

    private fun setupRecyclerView() {
        binding.notificationsContainer.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterNotifications
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userState.collect { state ->
                    when (state) {
                        is StateHandler.Error -> Toast.makeText(
                            requireContext(),
                            "Не удалось получить данные",
                            Toast.LENGTH_SHORT
                        ).show()

                        StateHandler.Initial -> Unit
                        StateHandler.Loading -> Unit
                        is StateHandler.Success -> {
                            binding.name.text = state.data.name

                            if (!state.data.avatarPath.isNullOrEmpty() && File(state.data.avatarPath).exists()) {
                                Glide.with(requireContext())
                                    .load(File(state.data.avatarPath).toUri())
                                    .circleCrop()
                                    .into(binding.avatar)
                            } else {
                                binding.avatar.setImageResource(R.drawable.avatar_placeholder)
                            }
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.notificationState.collect { state ->
                when (state) {
                    is StateHandler.Error -> Toast.makeText(
                        requireContext(),
                        "Не удалось получить данные",
                        Toast.LENGTH_SHORT
                    ).show()

                    StateHandler.Initial -> Unit
                    StateHandler.Loading -> Unit
                    is StateHandler.Success -> {
                        adapterNotifications.loadNewList(state.data.map { it.time })
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.notificationEnabledState.collect { state ->
                when (state) {
                    is StateHandler.Error -> Unit
                    StateHandler.Initial -> Unit
                    StateHandler.Loading -> Unit
                    is StateHandler.Success -> {
                        binding.sendNotificationSwitcher.isChecked = state.data
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.fingerPrintEnabledState.collect { state ->
                when (state) {
                    is StateHandler.Error -> Unit
                    StateHandler.Initial -> Unit
                    StateHandler.Loading -> Unit
                    is StateHandler.Success -> {
                        binding.fingerprintSwitcher.isChecked = state.data
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun setupClickListeners() {
        binding.addNotification.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }

            val alarmManager =
                requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
            if (!alarmManager.canScheduleExactAlarms()) {
                startActivity(Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM))
            }

            showBottomSheetWithPicker()
        }

        binding.avatar.setOnClickListener {
            openImagePicker()
        }

        binding.sendNotificationSwitcher.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setNotificationEnabled(isChecked)
        }

        binding.fingerprintSwitcher.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setFingerPrintEnabled(isChecked)
        }

        binding.logOut.setOnClickListener {
            viewModel.signOut()

            val intent = Intent(requireContext(), WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        imagePickerLauncher.launch(intent)
    }

    private fun saveImageToInternalStorage(uri: Uri) {
        val iStream = requireContext().contentResolver.openInputStream(uri)

        val fileName = "user_avatar${UUID.randomUUID()}.jpg"
        val file = File(requireContext().filesDir, fileName)

        iStream?.use { i ->
            FileOutputStream(file).use { out ->
                i.copyTo(out)
            }
        }

        val savedPath = file.absolutePath
        viewModel.editUser(savedPath)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun showBottomSheetWithPicker() {
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(CLOCK_24H)
            .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
            .setTheme(R.style.BaseTheme_TimePicker)
            .build()

        val bottomSheet = BottomSheetDialog(requireContext())
        val view =
            LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_content, null)
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
            viewModel.addNotification(timeText)

            ReminderManager.scheduleReminder(
                requireContext(),
                (hoursText.text as String).toInt(),
                (minutesText.text as String).toInt()
            )
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