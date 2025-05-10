package com.example.hardmad2024_1

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hardmad2024_1.presentation.settings_screen.SettingsFragment
import com.example.hardmad2024_1.screens.NotificationItem
import com.example.hardmad2024_1.screens.SettingsScreen
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsTest{

    @Test
    fun settingsViewTest(){
        launchFragment()

        SettingsScreen{
            checkGreetTextVisibility()

            avatarImageView {
                isVisible()
                isDisplayed()
            }

            nameTextView {
                isVisible()
                isDisplayed()
                hasText("Иван Иванов")
            }

            notificationIcon {
                isVisible()
                isDisplayed()
            }

            notificationTextView{
                isVisible()
                isDisplayed()
                hasText(R.string.send_notifications)
            }

            fingerprintIcon {
                isVisible()
                isDisplayed()
            }

            fingerprintTextView{
                isVisible()
                isDisplayed()
                hasText(R.string.enter_fingerprint)
            }

            testSwitchers()


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
    }

    private fun launchFragment(){
        FragmentScenario.launchInContainer(
            fragmentClass = SettingsFragment::class.java,
            themeResId = R.style.Theme_HardMad20241
        )
    }
}