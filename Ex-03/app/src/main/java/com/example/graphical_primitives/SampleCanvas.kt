package com.example.graphical_primitives

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.widget.ImageView

object SampleCanvas {
    fun drawOnCanvas(imageView: ImageView) {
        val bitmap = Bitmap.createBitmap(720, 1280, Bitmap.Config.RGB_565)
        imageView.setImageBitmap(bitmap)

        val canvas = Canvas(bitmap)
        val textPaint = Paint().apply {
            color = Color.WHITE
            textSize = 55F
        }

        val rectanglePaint = Paint().apply {
            color = Color.RED
        }

        val circlePaint = Paint().apply {
            color = Color.GREEN
        }

        val squarePaint = Paint().apply {
            color = Color.BLUE
        }

        val linePaint = Paint().apply {
            color = Color.YELLOW
        }

        canvas.drawText("Rectangle", 350F, 150F, textPaint)
        canvas.drawRect(400F, 200F, 650F, 700F, rectanglePaint)

        canvas.drawText("Circle", 120F, 150F, textPaint)
        canvas.drawCircle(200F, 350F, 150F, circlePaint)

        canvas.drawText("Square", 120F, 800F, textPaint)
        canvas.drawRect(50F, 850F, 350F, 1150F, squarePaint)

        canvas.drawText("Line", 480F, 800F, textPaint)
        canvas.drawLine(520F, 850F, 520F, 1150F, linePaint)


    }
}
