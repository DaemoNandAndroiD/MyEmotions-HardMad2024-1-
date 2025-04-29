package com.example.hardmad2024_1.presentation.journal_screen.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import com.example.hardmad2024_1.presentation.util.classes.ShortNote

class CustomProgressView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0, val colors:List<ShortNote>, val totalAmount:Int)
    : View(context, attrs, defStyleAttr, defStyleRes) {

    private fun linearGradientShader(colors:IntArray) : Shader{
        return LinearGradient(
            0f, 0f, width.toFloat(), height.toFloat(),
            colors,
            null,
            Shader.TileMode.CLAMP
        )
    }

    private fun arcPaint(linearGradient:Shader) : Paint{
        return Paint().apply{
            shader = linearGradient
            style = Paint.Style.STROKE
            strokeWidth = 60f
            strokeCap = Paint.Cap.ROUND
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val rectF = RectF(30f, 30f, width.toFloat() -30f, height.toFloat() - 30f)

        canvas.drawArc(rectF, 0f, 360f, false, Paint().apply {
            color = Color.parseColor("#1A1A1A")
            style = Paint.Style.STROKE
            strokeWidth = 60f
            strokeCap = Paint.Cap.ROUND
        })

        var startAngle = 270f
        var sweepAngle:Float

        repeat(colors.size){
            sweepAngle = 360f / totalAmount * colors[it].amount

            val colorArray = when(colors[it].color){
                Color.YELLOW -> intArrayOf(Color.parseColor("#FFFF33"), Color.parseColor("#FFAA00"))
                Color.RED -> intArrayOf(Color.parseColor("#FF5533"), Color.parseColor("#FF0000"))
                Color.GREEN -> intArrayOf(Color.parseColor("#33FFBB"), Color.parseColor("#00FF55"))
                else -> intArrayOf(Color.parseColor("#33DDFF"), Color.parseColor("#00AAFF"))
            }

            canvas.drawArc(rectF, startAngle, sweepAngle, false, arcPaint(linearGradientShader(colorArray)))

            startAngle = if(startAngle + sweepAngle >= 360f) startAngle + sweepAngle - 360f else startAngle + sweepAngle
        }
    }
}