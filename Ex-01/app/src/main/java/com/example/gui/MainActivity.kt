package com.example.gui

import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val b1 = findViewById<Button>(R.id.fontsize)
        val text = findViewById<TextView>(R.id.textView)
        val b2 = findViewById<Button>(R.id.fontcolor)
        val b3 = findViewById<Button>(R.id.bgcolor)
        val full = findViewById<ConstraintLayout>(R.id.main)
        // Change Font Size
        b1.setOnClickListener{

            text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40f)
        }
        // Change Font Color
        b2.setOnClickListener{
            text.setTextColor(Color.RED)
        }
        // Change Background Color
        b3.setOnClickListener{
            full.setBackgroundColor(Color.BLUE)
        }
    }
}