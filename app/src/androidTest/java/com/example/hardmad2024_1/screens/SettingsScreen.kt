package com.example.hardmad2024_1.screens

import android.view.View
import com.example.hardmad2024_1.R
import com.example.hardmad2024_1.presentation.fragments.SettingsFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.switch.KSwitch
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object SettingsScreen: KScreen<SettingsScreen>() {

    val greetTextView = KTextView{
        withId(R.id.greet_text)
    }

    val avatarImageView = KImageView{
        withId(R.id.avatar)
    }

    val nameTextView = KTextView{
        withId(R.id.name)
    }

    val notificationIcon = KImageView{
        withId(R.id.send_notification_icon)
    }

    val notificationTextView = KTextView{
        withId(R.id.send_notification_text)
    }

    val notificationSwitch = KSwitch{
        withId(R.id.send_notification_switcher)
    }

    val rvNotifications = KRecyclerView(
        builder = {withId(R.id.notifications_container)},
        itemTypeBuilder = {
            itemType(::NotificationItem)
        }
    )

    private val addButton = KButton{
        withId(R.id.add_notification)
    }

    val fingerprintIcon = KImageView{
        withId(R.id.fingerprint_icon)
    }

    val fingerprintTextView = KTextView{
        withId(R.id.fingerprint_text)
    }

    val fingerprintSwitch = KSwitch{
        withId(R.id.fingerprint_switcher)
    }

    val bottomSheetButton = KButton{
        withId(R.id.saveBtn)
    }

    fun checkGreetTextVisibility(){
        greetTextView{
            isVisible()
        }
    }

    fun clickAddButton(){
        addButton.click()
    }

    fun bottomSheetAction(){
        bottomSheetButton.click()
    }

    fun checkRecyclerContent(size:Int){
        rvNotifications.hasSize(size)
    }

    fun testSwitchers(){
        notificationSwitch.click()
        fingerprintSwitch.click()
    }

    override val layoutId: Int?
        get() = R.layout.settings_fragment
    override val viewClass: Class<*>?
        get() = SettingsFragment::class.java
}

class NotificationItem(matcher: Matcher<View>) :KRecyclerItem<NotificationItem>(matcher){
    val time = KTextView{withId(R.id.time)}
    val deleteButton = KButton{withId(R.id.delete_btn)}
}