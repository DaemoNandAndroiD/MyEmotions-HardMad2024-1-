package com.example.hardmad2024_1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.TypedValue
import android.view.KeyEvent
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
import androidx.core.view.marginRight
import androidx.core.view.size
import com.example.hardmad2024_1.databinding.AddNoteDetailsActivityBinding
import com.example.hardmad2024_1.utilities.CardData
import com.example.hardmad2024_1.utilities.toPx
import org.w3c.dom.Text

class AddNoteDetailsActivity:ComponentActivity() {
    private lateinit var binding:AddNoteDetailsActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val card = intent.getParcelableExtra<CardData>("card")

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
            startActivity(intent)
        }

        card?.let {
            editCard(it, binding.card)
        }

        binding.activityContainer.addView(addNewTextView("Приём пищи"), binding.activityContainer.size -2)
        binding.activityContainer.addView(addNewTextView("Встреча с друзьями"), binding.activityContainer.size -2)
        binding.activityContainer.addView(addNewTextView("Тренировка"), binding.activityContainer.size -2)
        binding.activityContainer.addView(addNewTextView("Хобби"), binding.activityContainer.size -2)
        binding.activityContainer.addView(addNewTextView("Отдых"), binding.activityContainer.size -2)
        binding.activityContainer.addView(addNewTextView("Поездка"), binding.activityContainer.size -2)

        binding.addActivityButton.setOnClickListener {
            binding.editTextActivity.visibility = VISIBLE
        }

        binding.editTextActivity.setOnEditorActionListener{ v, keyCode, event ->
            if (keyCode == EditorInfo.IME_ACTION_DONE) {
                binding.activityContainer.addView(addNewTextView(binding.editTextActivity.text.toString()), binding.activityContainer.size -2)
                binding.editTextActivity.visibility = GONE
                binding.editTextActivity.text = null
                true
            }

            false
        }

        binding.humanContainer.addView(addNewTextView("Один"), binding.humanContainer.size -2)
        binding.humanContainer.addView(addNewTextView("Друзья"), binding.humanContainer.size -2)
        binding.humanContainer.addView(addNewTextView("Семья"), binding.humanContainer.size -2)
        binding.humanContainer.addView(addNewTextView("Коллеги"), binding.humanContainer.size -2)
        binding.humanContainer.addView(addNewTextView("Партнёр"), binding.humanContainer.size -2)
        binding.humanContainer.addView(addNewTextView("Питомцы"), binding.humanContainer.size -2)

        binding.addHumanButton.setOnClickListener {
            binding.editTextHuman.visibility = VISIBLE
        }

        binding.editTextHuman.setOnEditorActionListener{ v, keyCode, event ->
            if (keyCode == EditorInfo.IME_ACTION_DONE) {
                binding.humanContainer.addView(addNewTextView(binding.editTextHuman.text.toString()), binding.humanContainer.size -2)
                binding.editTextHuman.visibility = GONE
                binding.editTextHuman.text = null
                true
            }

            false
        }

        binding.placeContainer.addView(addNewTextView("Дом"), binding.placeContainer.size -2)
        binding.placeContainer.addView(addNewTextView("Работа"), binding.placeContainer.size -2)
        binding.placeContainer.addView(addNewTextView("Школа"), binding.placeContainer.size -2)
        binding.placeContainer.addView(addNewTextView("Транспорт"), binding.placeContainer.size -2)
        binding.placeContainer.addView(addNewTextView("Улица"), binding.placeContainer.size -2)

        binding.addPlaceButton.setOnClickListener {
            binding.editTextPlace.visibility = VISIBLE
        }

        binding.editTextPlace.setOnEditorActionListener{ v, keyCode, event ->
            if (keyCode == EditorInfo.IME_ACTION_DONE) {
                binding.placeContainer.addView(addNewTextView(binding.editTextPlace.text.toString()), binding.placeContainer.size -2)
                binding.editTextPlace.visibility = GONE
                binding.editTextPlace.text = null
                true
            }

            false
        }
    }

    private fun addNewTextView(text:String):TextView{
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

    private fun editCard(card: CardData, cardView: View){
        cardView.background = resources.getDrawable(card.backgroundDrawable)
        cardView.findViewById<TextView>(R.id.date).text = card.date
        cardView.findViewById<ImageView>(R.id.icon).setImageResource(card.icon)
        val emotionType = cardView.findViewById<TextView>(R.id.emotion_type)
        emotionType.apply {
            text = card.emoteText
            setTextColor(resources.getColor(card.textColor))
        }
    }
}