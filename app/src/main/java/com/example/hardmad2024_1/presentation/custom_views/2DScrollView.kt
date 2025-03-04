package com.example.hardmad2024_1.presentation.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.HorizontalScrollView
import android.widget.ScrollView

class WScrollView : ScrollView {
    var sv: HorizontalScrollView? = null

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var ret = super.onTouchEvent(event)
        ret = ret or sv!!.onTouchEvent(event)
        return ret
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        var ret = super.onInterceptTouchEvent(event)
        ret = ret or sv!!.onInterceptTouchEvent(event)
        return ret
    }
}