package com.example.hardmad2024_1

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hardmad2024_1.presentation.fragments.OftenStatisticsFragment
import com.example.hardmad2024_1.presentation.util.classes.OftenStatisticsData
import com.example.hardmad2024_1.screens.OftenStatisticsScreen
import com.example.hardmad2024_1.screens.RVItem
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OftenStatisticsTest {

    @Test
    fun emptyTest(){
        launchFragment(data = arrayOf())

        OftenStatisticsScreen{
            title{
                isDisplayed()
                isVisible()
            }
            rvEmotions.hasSize(0)
        }
    }

    @Test fun filledTest(){
        launchFragment(data = arrayOf(
            OftenStatisticsData("Спокойствие", 1f,4, R.drawable.ic_green_emote, R.drawable.green_often_stat),
            OftenStatisticsData("Продуктивность", 0.75f, 3, R.drawable.ic_yellow_emote, R.drawable.yellow_often_stat),
            OftenStatisticsData("Счастье", 0.5f, 2, R.drawable.ic_yellow_emote_2, R.drawable.yellow_often_stat),
            OftenStatisticsData("Выгорание", 1f, 4, R.drawable.ic_blue_emote, R.drawable.blue_often_stat),
            OftenStatisticsData("Усталость", 0.25f, 1, R.drawable.ic_blue_emote_2, R.drawable.blue_often_stat)
        ))

        OftenStatisticsScreen{
            rvEmotions {
                hasSize(5)

                childAt<RVItem>(1){
                    iconView{
                        isDisplayed()
                        isVisible()
                    }

                    emotionCount{
                        isVisible()
                        hasText("3")
                    }

                    emoteText{
                        isVisible()
                        hasText("Продуктивность")
                    }
                }
            }
        }

    }



    private fun launchFragment(data:Array<OftenStatisticsData>){
        val fragment = OftenStatisticsFragment.createNewInstance(data)

        FragmentScenario.launchInContainer(
            fragmentClass = fragment::class.java,
            fragmentArgs = fragment.arguments
        )
    }
}