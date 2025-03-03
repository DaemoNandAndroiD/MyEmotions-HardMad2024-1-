package com.example.hardmad2024_1

import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hardmad2024_1.screens.JournalScreen
import com.example.hardmad2024_1.utilities.JournalFragmentData
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JournalScreenTest{

    @Test fun emptyTest(){
        launchFragment(arrayOf())

        JournalScreen{
            emotionsList.hasSize(0)
        }
    }

    @Test fun filledContent(){
        launchFragment(arrayOf(
            JournalFragmentData("сегодня, 12:00",
                "выгорание",
                R.color.blue_text,
                R.drawable.card_shape_blue,
                R.drawable.ic_blue_emote),
            JournalFragmentData("сегодня, 10:45",
                "спокойствие",
                R.color.green_text,
                R.drawable.card_shape_green,
                R.drawable.ic_green_emote),
            JournalFragmentData("вчера, 23:11",
                "продуктивность",
                R.color.yellow_text,
                R.drawable.card_shape_yellow,
                R.drawable.ic_yellow_emote),
            JournalFragmentData("вчера, 11:11",
                "выгорание",
                R.color.red_text,
                R.drawable.card_shape_red,
                R.drawable.ic_red_emote)
        ))

        JournalScreen{
            emotionsList.hasSize(4)
        }
    }

    private fun launchFragment(data:Array<JournalFragmentData>){
        val fragment = JournalFragment.createNewInstance(data)

        FragmentScenario.launchInContainer(
            fragmentClass = fragment::class.java,
            fragmentArgs = fragment.arguments,
            themeResId = R.style.Theme_HardMad20241
        )
    }
}