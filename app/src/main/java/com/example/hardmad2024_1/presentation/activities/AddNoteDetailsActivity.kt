package com.example.hardmad2024_1.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.size
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.databinding.AddNoteDetailsActivityBinding
import com.example.hardmad2024_1.presentation.util.extensions.toPx

class AddNoteDetailsActivity:ComponentActivity() {
    private lateinit var binding:AddNoteDetailsActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = AddNoteDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowCompat.setDecorFitsSystemWindows(window,false)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.navigationBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.saveBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra("isEmpty", false)
            startActivity(intent)
        }

        binding.activityContainer.addView(createTextView("Приём пищи"), binding.activityContainer.size -2)
        binding.activityContainer.addView(createTextView("Встреча с друзьями"), binding.activityContainer.size -2)
        binding.activityContainer.addView(createTextView("Тренировка"), binding.activityContainer.size -2)
        binding.activityContainer.addView(createTextView("Хобби"), binding.activityContainer.size -2)
        binding.activityContainer.addView(createTextView("Отдых"), binding.activityContainer.size -2)
        binding.activityContainer.addView(createTextView("Поездка"), binding.activityContainer.size -2)

        binding.addActivityButton.setOnClickListener {
            binding.editTextActivity.visibility = VISIBLE
        }

        binding.editTextActivity.setOnEditorActionListener{ v, keyCode, event ->
            if (keyCode == EditorInfo.IME_ACTION_DONE) {
                binding.activityContainer.addView(createTextView(binding.editTextActivity.text.toString()), binding.activityContainer.size -2)
                binding.editTextActivity.visibility = GONE
                binding.editTextActivity.text = null
                true
            }

            false
        }

        binding.humanContainer.addView(createTextView("Один"), binding.humanContainer.size -2)
        binding.humanContainer.addView(createTextView("Друзья"), binding.humanContainer.size -2)
        binding.humanContainer.addView(createTextView("Семья"), binding.humanContainer.size -2)
        binding.humanContainer.addView(createTextView("Коллеги"), binding.humanContainer.size -2)
        binding.humanContainer.addView(createTextView("Партнёр"), binding.humanContainer.size -2)
        binding.humanContainer.addView(createTextView("Питомцы"), binding.humanContainer.size -2)

        binding.addHumanButton.setOnClickListener {
            binding.editTextHuman.visibility = VISIBLE
        }

        binding.editTextHuman.setOnEditorActionListener{ v, keyCode, event ->
            if (keyCode == EditorInfo.IME_ACTION_DONE) {
                binding.humanContainer.addView(createTextView(binding.editTextHuman.text.toString()), binding.humanContainer.size -2)
                binding.editTextHuman.visibility = GONE
                binding.editTextHuman.text = null
                true
            }

            false
        }

        binding.placeContainer.addView(createTextView("Дом"), binding.placeContainer.size -2)
        binding.placeContainer.addView(createTextView("Работа"), binding.placeContainer.size -2)
        binding.placeContainer.addView(createTextView("Школа"), binding.placeContainer.size -2)
        binding.placeContainer.addView(createTextView("Транспорт"), binding.placeContainer.size -2)
        binding.placeContainer.addView(createTextView("Улица"), binding.placeContainer.size -2)

        binding.addPlaceButton.setOnClickListener {
            binding.editTextPlace.visibility = VISIBLE
        }

        binding.editTextPlace.setOnEditorActionListener{ v, keyCode, event ->
            if (keyCode == EditorInfo.IME_ACTION_DONE) {
                binding.placeContainer.addView(createTextView(binding.editTextPlace.text.toString()), binding.placeContainer.size -2)
                binding.editTextPlace.visibility = GONE
                binding.editTextPlace.text = null
                true
            }

            false
        }
    }

    private fun createTextView(text:String):TextView{
        val layoutParamsView = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        layoutParamsView.setMargins(0,0,4.toPx(this),4.toPx(this))

        val textView = TextView(this).apply {
            layoutParams = layoutParamsView
            setText(text)
            setTextColor(resources.getColor(R.color.white))
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 14F)
            typeface = resources.getFont(R.font.velasans_regular)
            background = resources.getDrawable(R.drawable.rounded_button)
            backgroundTintList = resources.getColorStateList(R.color.label_gray)
            setPadding(16.toPx(context),8.toPx(context),16.toPx(context),8.toPx(context))
            tag = false
        }

        textView.setOnClickListener {
            if(textView.tag == false){
                textView.backgroundTintList = resources.getColorStateList(R.color.gradient_gray)
                textView.tag = true
            }
            else{
                textView.backgroundTintList = resources.getColorStateList(R.color.label_gray)
                textView.tag = false
            }
        }

        return textView
    }
}