package com.example.hardmad2024_1

import androidx.compose.ui.test.isNotEnabled
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hardmad2024_1.presentation.add_note_activity.AddNoteActivity
import com.example.hardmad2024_1.screens.AddNoteScreen
import com.example.hardmad2024_1.screens.GridItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddNoteScreenTest {

    @get:Rule
    val activityRule = activityScenarioRule<AddNoteActivity>()

    @Test fun viewsTest(){
        AddNoteScreen{
            backBtn{
                isDisplayed()
                isVisible()
            }

            defaultText{
                isVisible()
                isDisplayed()
                hasText(R.string.base_add_note_text)
            }

            emotionTitle{
                isNotDisplayed()
            }

            emotionDescription{
                isNotDisplayed()
            }

            detailsBtn{
                isNotEnabled()
            }

            gridRV.childAt<GridItem>(5){
                click()
                circleText.hasText(R.string.anxiety_title)
            }

            defaultText{
                isNotDisplayed()
            }

            emotionTitle{
                isVisible()
                isDisplayed()
                hasText(R.string.anxiety_title)
            }

            emotionDescription{
                isVisible()
                isDisplayed()
                hasText(R.string.anxiety_description)
            }

            detailsBtn{
                isEnabled()
            }
        }
    }
}