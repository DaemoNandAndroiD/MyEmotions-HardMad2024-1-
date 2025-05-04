package com.example.hardmad2024_1

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hardmad2024_1.presentation.statistics.general_statistics.GeneralStatisticsFragment
import com.example.hardmad2024_1.screens.GeneralStatisticsScreen
import com.example.hardmad2024_1.presentation.util.classes.GeneralStatisticsData
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GeneralStatisticsTest {

    @Test
    fun testThreeCircles(){
        launchFragment(GeneralStatisticsData(3, listOf(33f,33f, 33f)))

        GeneralStatisticsScreen{
            recordsCount{
                isVisible()
                hasText("3 записей")
            }

            circleTextView1{
                isDisplayed()
                isVisible()
                hasText("33%")
            }

            circleTextView2{
                isDisplayed()
                isVisible()
                hasText("33%")
            }

            circleTextView3{
                isDisplayed()
                isVisible()
                hasText("33%")
            }

            circleTextView4{
                isNotDisplayed()
            }
        }
    }

    @Test
    fun emptyTest(){
        launchFragment(GeneralStatisticsData())

        GeneralStatisticsScreen{
            title{
                isVisible()
                isDisplayed()
                hasText(R.string.stat_general_title)
            }

            recordsCount{
                isVisible()
                hasText("0 записей")
            }

            circleTextView1{
                isNotDisplayed()
            }

            circleTextView2{
                isNotDisplayed()
            }

            circleTextView3{
                isNotDisplayed()
            }

            circleTextView4{
                isNotDisplayed()
            }
        }
    }

    private fun launchFragment(data: GeneralStatisticsData){
        val fragment = GeneralStatisticsFragment.createNewInstance(data)

        FragmentScenario.launchInContainer(
            fragmentClass = fragment::class.java,
            fragmentArgs = fragment.arguments
        )
    }
}