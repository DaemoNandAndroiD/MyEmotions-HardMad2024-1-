package com.example.hardmad2024_1

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hardmad2024_1.presentation.statistics.week_statistics.WeekStatisticsFragment
import com.example.hardmad2024_1.presentation.util.classes.WeekStatisticsData
import com.example.hardmad2024_1.screens.ItemRecords
import com.example.hardmad2024_1.screens.PerWeekStatisticsScreen
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PerWeekTest {

    @Test
    fun emptyTest(){
        launchFragment(arrayOf(
            WeekStatisticsData(
                date = "3 марта",
                dayOfWeek = "Понедельник",
                emotionsTexts = listOf(),
                icons = listOf()
            ),
            WeekStatisticsData(
                date = "4 марта",
                dayOfWeek = "Вторник",
                emotionsTexts = listOf(),
                icons = listOf()
            ),
            WeekStatisticsData(
                date = "5 марта",
                dayOfWeek = "Среда",
                emotionsTexts = listOf(),
                icons = listOf()
            ),
            WeekStatisticsData(
                date = "6 марта",
                dayOfWeek = "",
                emotionsTexts = listOf(),
                icons = listOf()
            ),
            WeekStatisticsData(
                date = "7 марта",
                dayOfWeek = "",
                emotionsTexts = listOf(),
                icons = listOf()
            ),
            WeekStatisticsData(
                date = "8 марта",
                dayOfWeek = "",
                emotionsTexts = listOf(),
                icons = listOf()
            ),
            WeekStatisticsData(
                date = "9 марта",
                dayOfWeek = "",
                emotionsTexts = listOf(),
                icons = listOf()
            )
        ))

        PerWeekStatisticsScreen{
            title{
                isDisplayed()
                isVisible()
                hasText(R.string.week_stat_title)
            }

            recordsContainer.hasSize(7)

            recordsContainer.childAt<ItemRecords>(0){
                dayOfWeek.hasText("Понедельник")
            }
        }
    }

    private fun launchFragment(data: Array<WeekStatisticsData>){
        val fragment = WeekStatisticsFragment.createNewInstance(data)

        FragmentScenario.launchInContainer(
            fragmentClass = fragment::class.java,
            fragmentArgs = fragment.arguments
        )
    }
}