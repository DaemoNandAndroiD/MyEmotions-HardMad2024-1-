package com.example.hardmad2024_1

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hardmad2024_1.presentation.add_note_details_screen.AddNoteDetailsActivity
import com.example.hardmad2024_1.screens.AddNoteDetailsScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddNoteDetailsTest {

    @get:Rule
    val activityRule = activityScenarioRule<AddNoteDetailsActivity>()

    @Test fun viewsTest(){
        AddNoteDetailsScreen{
            backBtn{
                isVisible()
                isDisplayed()
            }
            title{
                isVisible()
                isDisplayed()
                hasText(R.string.note_details_title)
            }
            dateText{
                isVisible()
                isDisplayed()
            }
            emotionType{
                isVisible()
                isDisplayed()
            }
            icon{
                isVisible()
                isDisplayed()
            }
            noteTextActivity{
                isVisible()
                hasText(R.string.note_Activity_title)
            }
            notePlaceActivity{
                isVisible()
                hasText(R.string.place_title)
            }
            notePeopleActivity{
                isVisible()
                hasText(R.string.human_title)
            }
            saveBtn{
                isVisible()
                isEnabled()
            }
        }
    }
}