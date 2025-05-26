package com.example.flashcardsimad5112a2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // Switch to the next page
        val btnstart = findViewById<Button>(R.id.btnstart)
        btnstart.setOnClickListener {
            val Intent = Intent (this, Easy::class.java)
            startActivity(Intent)
        }
    }
}

/*The below reference is for the Background:
    Student Manual
    Varsity College
    Page: 71-74
    Adding a background image

 */