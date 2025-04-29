package com.example.hardmad2024_1.presentation.add_note_activity

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.databinding.AddNoteActivityBinding
import com.example.hardmad2024_1.domain.models.EmotionColor
import com.example.hardmad2024_1.presentation.activities.AddNoteDetailsActivity
import com.example.hardmad2024_1.presentation.util.classes.CircleClass
import com.example.hardmad2024_1.presentation.util.adapters.EmotionCirclesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddNoteActivity : ComponentActivity() {
    private lateinit var binding: AddNoteActivityBinding

    private lateinit var recyclerAdapter: EmotionCirclesAdapter

    private val viewModel by viewModels<AddNoteViewModel>()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        recyclerAdapter = EmotionCirclesAdapter(resources) { model ->
            binding.defaultText.visibility = GONE
            binding.emotionText.visibility = VISIBLE

            binding.emotionTitle.text = resources.getString(model.name)
            binding.emotionTitle.setTextColor(
                ResourcesCompat.getColor(
                    resources, when (model.color) {
                        EmotionColor.BLUE -> R.color.blue_text
                        EmotionColor.GREEN -> R.color.green_text
                        EmotionColor.RED -> R.color.red_text
                        EmotionColor.YELLOW -> R.color.yellow_text
                    }, null
                )
            )
            binding.emotionDescription.text = resources.getString(model.description)

            binding.detailsBtn.apply {
                isEnabled = true
                setImageResource(R.drawable.ic_forward_black)
                backgroundTintList = ResourcesCompat.getColorStateList(resources,R.color.white, null)
            }

            binding.detailsBtn.setOnClickListener {
                val intent = Intent(
                    this,
                    AddNoteDetailsActivity::class.java
                )
                startActivity(intent)
            }
        }

        observeViewModel()
        setupRecycler()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = AddNoteActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        setupWindow()
        setupOnClickListeners()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect { list ->
                if (list.isNotEmpty()) {
                    recyclerAdapter.loadNewList(list)
                }
            }
        }
    }

    private fun setupWindow() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.navigationBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun setupRecycler() {
        val recyclerView = binding.recyclerView
        val layoutManager = GridLayoutManager(this, 4)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerAdapter
    }

    private fun setupOnClickListeners() {
        binding.detailsBtn.isEnabled = false

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}