package com.example.flashcardsimad5112a2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    // Declare UI components
    private lateinit var txtFinalScore: TextView
    private lateinit var txtMessage: TextView
    private lateinit var btnReview: Button

    // Variables to hold the user's score and total questions
    private var score = 0
    private var total = 0

    // List of flashcards used for reviewing answers
    private lateinit var flashcards: ArrayList<Easy.Flashcard>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        // Link UI components to their XML counterparts
        txtFinalScore = findViewById(R.id.txtfeed)
        txtMessage = findViewById(R.id.txtmessage)
        btnReview = findViewById(R.id.btnreview)

        // Retrieve score and flashcard list passed from Easy activity
        score = intent.getIntExtra("SCORE", 0)
        total = intent.getIntExtra("TOTAL", 0)
        flashcards = intent.getSerializableExtra("FLASHCARDS") as ArrayList<Easy.Flashcard>

        // Display final score
        txtFinalScore.text = "Your Score: $score / $total"

        // Display encouraging message based on performance
        txtMessage.text = if (score > 3) {
            "Great job!"
        } else {
            "Keep trying!"
        }

        // Set up Review button to open ReviewActivity and pass flashcards
        btnReview.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putExtra("FLASHCARDS", flashcards)
            startActivity(intent)
        }
    }
}


