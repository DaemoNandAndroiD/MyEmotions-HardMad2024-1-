package com.example.hardmad2024_1

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hardmad2024_1.presentation.welcome_screen.WelcomeActivity
import com.example.hardmad2024_1.screens.AddNoteDetailsScreen
import com.example.hardmad2024_1.screens.AddNoteScreen
import com.example.hardmad2024_1.screens.GridItem
import com.example.hardmad2024_1.screens.JournalScreen
import com.example.hardmad2024_1.screens.MainScreen
import com.example.hardmad2024_1.screens.NotificationItem
import com.example.hardmad2024_1.screens.RecordsItem
import com.example.hardmad2024_1.screens.SettingsScreen
import com.example.hardmad2024_1.screens.StatisticsScreen
import com.example.hardmad2024_1.screens.WelcomeScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SuccessTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<WelcomeActivity>()

    @Test
    fun happyPass() = run{

        WelcomeScreen{
            clickEnterBtn()
        }

        JournalScreen{
            progressBarEmpty{
                isVisible()
                isDisplayed()
            }

            addBtn{
                isVisible()
                click()
            }
        }

        AddNoteScreen{
            detailsBtn.click()
            emotionTitle.isNotDisplayed()

            gridRV.childAt<GridItem>(5){
                click()
            }

            emotionTitle.isDisplayed()
            detailsBtn.isClickable()
            detailsBtn.click()
        }

        AddNoteDetailsScreen{
            flakySafely {  saveBtn.click()}
        }

        JournalScreen{
            progressBarEmpty{
                isNotDisplayed()
            }
            emotionsList.childAt<RecordsItem>(0){
                click()
            }
        }

        AddNoteDetailsScreen{
            backBtn.click()
        }

        MainScreen{
            navigateToSettings()
        }

        SettingsScreen{
            checkRecyclerContent(0)
            clickAddButton()
            bottomSheetAction()
            checkRecyclerContent(1)

            rvNotifications.childAt<NotificationItem>(0){
                time{
                    isVisible()
                }
                deleteButton.click()
            }

            checkRecyclerContent(0)
        }

        MainScreen{
            navigateToStatistics()
        }

        StatisticsScreen{
            viewPager{
                swipeUp()
                swipeUp()
                swipeUp()
                swipeUp()
                swipeDown()
                swipeLeft()
                swipeLeft()
            }
        }
    }
}