package com.example.hardmad2024_1

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.hardmad2024_1.databinding.SettingsFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H
import java.sql.Time
import java.time.LocalTime
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class SettingsFragment: Fragment(R.layout.settings_fragment) {
    private lateinit var binding: SettingsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SettingsFragmentBinding.bind(view)

        binding.addNotification.setOnClickListener {
            val timePicker = fragmentManager?.let { it1 ->
                MaterialTimePicker.Builder()
                    .setTimeFormat(CLOCK_24H)
                    .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
                    .build()
            }

            val bottomSheet = context?.let { it1 -> BottomSheetDialog(it1) }
            val inflater = LayoutInflater.from(context)
            val bottomSheetContent = inflater.inflate(R.layout.bottom_sheet_content, null)

            val currentTime = LocalTime.now()
            val hours = currentTime.hour
            val minutes = currentTime.minute

            bottomSheetContent.findViewById<TextView>(R.id.hours).text = String.format("%02d", hours)
            bottomSheetContent.findViewById<TextView>(R.id.minutes).text = String.format("%02d", minutes)

            val clickableLayout = bottomSheetContent.findViewById<LinearLayout>(R.id.time_section)
            clickableLayout.setOnClickListener {
                fragmentManager?.let { it1 -> timePicker?.show(it1, "timePicker") }
            }

            bottomSheetContent.findViewById<AppCompatButton>(R.id.saveBtn).setOnClickListener {
                bottomSheet?.dismiss()
            }

            timePicker?.addOnPositiveButtonClickListener {
                bottomSheetContent.findViewById<TextView>(R.id.hours).text = String.format("%02d", timePicker.hour)
                bottomSheetContent.findViewById<TextView>(R.id.minutes).text = String.format("%02d", timePicker.minute)
            }

            bottomSheet?.setContentView(bottomSheetContent)
            bottomSheet?.show()
        }
    }
}