package com.example.hardmad2024_1

import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hardmad2024_1.databinding.JournalFragmentBinding
import com.example.hardmad2024_1.utilities.JournalFragmentData
import com.example.hardmad2024_1.utilities.JournalRecordsAdapter
import com.example.hardmad2024_1.utilities.ShortNote
import com.example.hardmad2024_1.utilities.toPx
import com.example.hardmad2024_1.views.CustomProgressView


class JournalFragment:Fragment(R.layout.journal_fragment){
    private lateinit var binding: JournalFragmentBinding
    private var data:Array<Parcelable> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelableArray(BUNDLE_KEY)?.let {
            data = it
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = JournalFragmentBinding.bind(view)

        binding.records.text = this.resources.getQuantityString(R.plurals.records, 5, 5)
        binding.perDay.text = this.resources.getQuantityString(R.plurals.records, 2, 2)
        binding.streak.text = this.resources.getQuantityString(R.plurals.days, 3, 3)

        val params = binding.progressBarContainer.layoutParams
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val calculatedWidth = screenWidth - 48.toPx(requireContext())
        params.width = calculatedWidth
        params.height = calculatedWidth
        binding.progressBarContainer.layoutParams = params

        if(requireActivity().intent.getBooleanExtra("isEmpty", true)){
            binding.progressBarEmpty.visibility = VISIBLE
            val rotateAnimation = AnimationUtils.loadAnimation(context, R.anim.rotation_gradient)
            binding.progressBarEmpty.animation = rotateAnimation
        }
        else{
            binding.progressBarEmpty.visibility = GONE
            val progressView = CustomProgressView(requireContext(),
                colors = listOf(
                    ShortNote(color = Color.YELLOW, amount = 4),
                    ShortNote(color = Color.RED, amount = 1),
                    ShortNote(color = Color.GREEN, amount = 2),
                    ShortNote(color = Color.BLUE, amount = 1)),
                totalAmount = 10)
            progressView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            binding.progressBarContainer.addView(progressView)
        }



        binding.addBtn.setOnClickListener {
            startActivity(Intent(context, AddNoteActivity::class.java))
        }


        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = JournalRecordsAdapter(resources, data?.let {
            data.toList() as List<JournalFragmentData>
        } ?: listOf(),
            {card:JournalFragmentData->
                startActivity(Intent(requireContext(), AddNoteDetailsActivity::class.java).apply {
                    putExtra("card", card)
                })
            }
        )

        binding.emotionsList.apply {
            this.layoutManager = layoutManager
            this.adapter = adapter
        }
    }

    companion object{
        const val BUNDLE_KEY = "JOURNAL_DATA"

        fun createNewInstance(journalFragmentData: Array<JournalFragmentData>):JournalFragment{
            return JournalFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArray(BUNDLE_KEY, journalFragmentData)
                }
            }
        }
    }
}