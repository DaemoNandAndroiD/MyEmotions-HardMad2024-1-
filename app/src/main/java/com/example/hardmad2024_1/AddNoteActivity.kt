package com.example.hardmad2024_1

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.View.OnScrollChangeListener
import android.view.View.VISIBLE
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.constraintlayout.widget.ConstraintSet.Motion
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hardmad2024_1.databinding.AddNoteActivityBinding
import com.example.hardmad2024_1.utilities.CardData
import com.example.hardmad2024_1.utilities.CircleClass
import com.example.hardmad2024_1.utilities.EmotionCirclesAdapter
import com.example.hardmad2024_1.utilities.EmotionItemDecoration
import com.example.hardmad2024_1.utilities.WScrollView
import com.example.hardmad2024_1.utilities.animateScale
import com.example.hardmad2024_1.utilities.toPx
import java.time.LocalTime


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
                "Ярость" -> listOf(R.color.red_text, R.drawable.card_shape_red, R.drawable.ic_red_emote)
                "Напряжение" -> listOf(R.color.red_text, R.drawable.card_shape_red, R.drawable.ic_red_emote)
                "Зависть" -> listOf(R.color.red_text, R.drawable.card_shape_red, R.drawable.ic_red_emote)
                "Беспокойство" -> listOf(R.color.red_text, R.drawable.card_shape_red, R.drawable.ic_red_emote)
                "Возбуждение" -> listOf(R.color.yellow_text, R.drawable.card_shape_yellow, R.drawable.ic_yellow_emote)
                "Восторг" -> listOf(R.color.yellow_text, R.drawable.card_shape_yellow, R.drawable.ic_yellow_emote)
                "Уверенность" -> listOf(R.color.yellow_text, R.drawable.card_shape_yellow, R.drawable.ic_yellow_emote)
                "Счастье" -> listOf(R.color.yellow_text, R.drawable.card_shape_yellow, R.drawable.ic_yellow_emote)
                "Выгорание" -> listOf(R.color.blue_text, R.drawable.card_shape_blue, R.drawable.ic_blue_emote)
                "Усталость" -> listOf(R.color.blue_text, R.drawable.card_shape_blue, R.drawable.ic_blue_emote)
                "Депрессия" -> listOf(R.color.blue_text, R.drawable.card_shape_blue, R.drawable.ic_blue_emote)
                "Апатия" -> listOf(R.color.blue_text, R.drawable.card_shape_blue, R.drawable.ic_blue_emote)
                "Спокойствие" -> listOf(R.color.green_text, R.drawable.card_shape_green, R.drawable.ic_green_emote)
                "Удовлетворённость" -> listOf(R.color.green_text, R.drawable.card_shape_green, R.drawable.ic_green_emote)
                "Благодарность" -> listOf(R.color.green_text, R.drawable.card_shape_green, R.drawable.ic_green_emote)
                "Защищённость" -> listOf(R.color.green_text, R.drawable.card_shape_green, R.drawable.ic_green_emote)
                else -> listOf()
            }

            startActivity(Intent(this, AddNoteDetailsActivity::class.java)
                .putExtra("card",
                    CardData(
                        date = "сегодня, " + String.format("%02d",LocalTime.now().hour) + ":" + String.format("%02d",LocalTime.now().minute),
                        emoteText = binding.emotionTitle.text.toString().toLowerCase(),
                        textColor = resources[0],
                        backgroundDrawable = resources[1],
                        icon = resources[2]
                    )
                ))
        }


        val recyclerView = binding.recyclerView
        val layoutManager = GridLayoutManager(this,4, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = EmotionCirclesAdapter(listOf(
            CircleClass(resources.getColorStateList(R.color.gradient_red), "Ярость", "чувство, вызванное несправедливостью или разочарованием"),
        CircleClass(resources.getColorStateList(R.color.gradient_red), "Напряжение","состояние внутреннего стресса и готовности к действию"),
        CircleClass(resources.getColorStateList(R.color.gradient_yellow), "Возбуждение","чувство сильного интереса и активности"),
        CircleClass(resources.getColorStateList(R.color.gradient_yellow), "Восторг","чувство огромного удовольствия и радости"),
        CircleClass(resources.getColorStateList(R.color.gradient_red), "Зависть","ощущение желания того, что есть у другого, и неудовлетворенности от этого"),
        CircleClass(resources.getColorStateList(R.color.gradient_red), "Беспокойство","чувство тревоги и волнения о том, что может произойти в будущем"),
        CircleClass(resources.getColorStateList(R.color.gradient_yellow), "Уверенность","внутреннее ощущение силы и веры в свои способности и возможности"),
        CircleClass(resources.getColorStateList(R.color.gradient_yellow), "Счастье","состояние радости и удовлетворения от жизни, от того, что все идет хорошо"),
        CircleClass(resources.getColorStateList(R.color.gradient_blue), "Выгорание","эмоциональное истощение от постоянного стресса или перегрузки"),
        CircleClass(resources.getColorStateList(R.color.gradient_blue), "Усталость","ощущение, что необходимо отдохнуть, когда энергия на исходе"),
        CircleClass(resources.getColorStateList(R.color.gradient_green), "Спокойствие","ощущение внутреннего мира и гармонии, когда ничего не тревожит"),
        CircleClass(resources.getColorStateList(R.color.gradient_green), "Удовлетворённость","чувство довольства от того, что все в жизни устроено так, как хотелось бы"),
        CircleClass(resources.getColorStateList(R.color.gradient_blue), "Депрессия","чувство безысходности и апатии, когда кажется"),
        CircleClass(resources.getColorStateList(R.color.gradient_blue), "Апатия","полное отсутствие интереса к жизни и окружающим"),
        CircleClass(resources.getColorStateList(R.color.gradient_green), "Благодарность","чувство признательности за то, что есть, за то, что дарует жизнь"),
        CircleClass(resources.getColorStateList(R.color.gradient_green), "Защищённость","ощущение безопасности, когда ничего не угрожает")
        ),binding.emotionTitle, binding.emotionDescription) {
            binding.defaultText.visibility = GONE
            binding.emotionText.visibility = VISIBLE

            binding.detailsBtn.apply {
                isEnabled = true
                setImageResource(R.drawable.ic_forward_black)
                backgroundTintList = resources.getColorStateList(R.color.white)
            }

            recyclerView.invalidateItemDecorations()
        }

        val itemDecoration = EmotionItemDecoration { null }
        recyclerView.addItemDecoration(itemDecoration)


        var currentScaledView:View? = null

        binding.zoomLayout.setOnTouchListener{view, event->
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
        }
    }
}