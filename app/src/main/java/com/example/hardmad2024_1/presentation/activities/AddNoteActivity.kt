package com.example.hardmad2024_1.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.databinding.AddNoteActivityBinding
import com.example.hardmad2024_1.presentation.util.classes.JournalFragmentData
import com.example.hardmad2024_1.presentation.util.classes.CircleClass
import com.example.hardmad2024_1.presentation.util.adapters.EmotionCirclesAdapter
import java.time.LocalTime
import java.util.Locale


class AddNoteActivity:ComponentActivity() {
    private lateinit var binding:AddNoteActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = AddNoteActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowCompat.setDecorFitsSystemWindows(window,false)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.navigationBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.detailsBtn.isEnabled = false

        binding.detailsBtn.setOnClickListener {
            val resources:List<Int> = when(binding.emotionTitle.text.toString()){
                "Ярость" -> listOf(
                    R.color.red_text,
                    R.drawable.card_shape_red,
                    R.drawable.ic_red_emote
                )
                "Напряжение" -> listOf(
                    R.color.red_text,
                    R.drawable.card_shape_red,
                    R.drawable.ic_red_emote
                )
                "Зависть" -> listOf(
                    R.color.red_text,
                    R.drawable.card_shape_red,
                    R.drawable.ic_red_emote
                )
                "Беспокойство" -> listOf(
                    R.color.red_text,
                    R.drawable.card_shape_red,
                    R.drawable.ic_red_emote
                )
                "Возбуждение" -> listOf(
                    R.color.yellow_text,
                    R.drawable.card_shape_yellow,
                    R.drawable.ic_yellow_emote
                )
                "Восторг" -> listOf(
                    R.color.yellow_text,
                    R.drawable.card_shape_yellow,
                    R.drawable.ic_yellow_emote
                )
                "Уверенность" -> listOf(
                    R.color.yellow_text,
                    R.drawable.card_shape_yellow,
                    R.drawable.ic_yellow_emote
                )
                "Счастье" -> listOf(
                    R.color.yellow_text,
                    R.drawable.card_shape_yellow,
                    R.drawable.ic_yellow_emote
                )
                "Выгорание" -> listOf(
                    R.color.blue_text,
                    R.drawable.card_shape_blue,
                    R.drawable.ic_blue_emote
                )
                "Усталость" -> listOf(
                    R.color.blue_text,
                    R.drawable.card_shape_blue,
                    R.drawable.ic_blue_emote
                )
                "Депрессия" -> listOf(
                    R.color.blue_text,
                    R.drawable.card_shape_blue,
                    R.drawable.ic_blue_emote
                )
                "Апатия" -> listOf(
                    R.color.blue_text,
                    R.drawable.card_shape_blue,
                    R.drawable.ic_blue_emote
                )
                "Спокойствие" -> listOf(
                    R.color.green_text,
                    R.drawable.card_shape_green,
                    R.drawable.ic_green_emote
                )
                "Удовлетворённость" -> listOf(
                    R.color.green_text,
                    R.drawable.card_shape_green,
                    R.drawable.ic_green_emote
                )
                "Благодарность" -> listOf(
                    R.color.green_text,
                    R.drawable.card_shape_green,
                    R.drawable.ic_green_emote
                )
                "Защищённость" -> listOf(
                    R.color.green_text,
                    R.drawable.card_shape_green,
                    R.drawable.ic_green_emote
                )
                else -> listOf()
            }

            startActivity(Intent(this, AddNoteDetailsActivity::class.java)
                .putExtra("card",
                    JournalFragmentData(
                        date = "сегодня, " + String.format("%02d",LocalTime.now().hour) + ":" + String.format("%02d",LocalTime.now().minute),
                        emoteText = binding.emotionTitle.text.toString()
                            .lowercase(Locale.getDefault()),
                        textColor = resources[0],
                        backgroundDrawable = resources[1],
                        icon = resources[2]
                    )
                ))
        }


        val recyclerView = binding.recyclerView
        val layoutManager = GridLayoutManager(this, 4)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = EmotionCirclesAdapter(this,listOf(
            CircleClass(resources.getColorStateList(R.color.gradient_red),
                getString(R.string.rage_title), getString(R.string.rage_description)),
        CircleClass(resources.getColorStateList(R.color.gradient_red),
            getString(R.string.stress_title), getString(R.string.stress_description)),
        CircleClass(resources.getColorStateList(R.color.gradient_yellow),
            getString(R.string.excitement_title), getString(R.string.excitement_description)),
        CircleClass(resources.getColorStateList(R.color.gradient_yellow),
            getString(R.string.delight_title), getString(R.string.delight_description)),
        CircleClass(resources.getColorStateList(R.color.gradient_red),
            getString(R.string.envy_title), getString(R.string.envy_description)),
        CircleClass(resources.getColorStateList(R.color.gradient_red),
            getString(R.string.anxiety_title), getString(R.string.anxiety_description)),
        CircleClass(resources.getColorStateList(R.color.gradient_yellow),
            getString(R.string.confidence_title), getString(R.string.confidence_description)),
        CircleClass(resources.getColorStateList(R.color.gradient_yellow),
            getString(R.string.happiness_title), getString(R.string.happiness_description)),
        CircleClass(resources.getColorStateList(R.color.gradient_blue),
            getString(R.string.burnout_title), getString(R.string.burnout_description)),
        CircleClass(resources.getColorStateList(R.color.gradient_blue),
            getString(R.string.tiredness_title), getString(R.string.tiredness_description)),
        CircleClass(resources.getColorStateList(R.color.gradient_green),
            getString(R.string.calmness_title), getString(R.string.calmness_description)),
        CircleClass(resources.getColorStateList(R.color.gradient_green),
            getString(R.string.satisfaction_title), getString(R.string.satisfaction_description)),
        CircleClass(resources.getColorStateList(R.color.gradient_blue),
            getString(R.string.depression_title), getString(R.string.depression_description)),
        CircleClass(resources.getColorStateList(R.color.gradient_blue),
            getString(R.string.apathy_title), getString(R.string.apathy_description)),
        CircleClass(resources.getColorStateList(R.color.gradient_green),
            getString(R.string.gratitude_title), getString(R.string.gratitude_description)),
        CircleClass(resources.getColorStateList(R.color.gradient_green),
            getString(R.string.security_title), getString(R.string.security_description))
        ),binding.emotionTitle, binding.emotionDescription) {
            binding.defaultText.visibility = GONE
            binding.emotionText.visibility = VISIBLE

            binding.detailsBtn.apply {
                isEnabled = true
                setImageResource(R.drawable.ic_forward_black)
                backgroundTintList = resources.getColorStateList(R.color.white)
            }
        }

        /*binding.zoomLayout.setOnTouchListener{view, event->
            if (event.action == MotionEvent.ACTION_MOVE) {
                val closestIndex = (layoutManager.findFirstVisibleItemPosition() - layoutManager.findLastVisibleItemPosition()) / 2

                recyclerView.getChildAt(closestIndex)?.let { closestView ->
                    if (closestView != currentScaledView) {
                        currentScaledView?.let { animateScale(it,1f) }
                        animateScale(closestView,1.3f)

                        currentScaledView = closestView
                    }
                }
            }

            false
        }*/
    }
}