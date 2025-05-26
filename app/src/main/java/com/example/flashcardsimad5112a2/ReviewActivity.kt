package com.example.flashcardsimad5112a2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReviewActivity : AppCompatActivity() {

    // Declare UI elements
    private lateinit var txtReviewContent: TextView
    private lateinit var btnExit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        // Link UI components to their XML layout IDs
        txtReviewContent = findViewById(R.id.txtreview)
        btnExit = findViewById(R.id.btnexit)

        // Retrieve the list of flashcards passed from the ScoreActivity
        val flashcards = intent.getSerializableExtra("FLASHCARDS") as ArrayList<Easy.Flashcard>

        // Build a string to display all flashcards and their correct answers
        val builder = StringBuilder()
        for ((index, card) in flashcards.withIndex()) {
            builder.append("${index + 1}. ${card.statement}\nAnswer: ${if (card.isTrue) "True" else "False"}\n\n")
        }

        // Set the review content to the TextView
        txtReviewContent.text = builder.toString()

        // Exit button closes all activities and exits the app
        btnExit.setOnClickListener {
            finishAffinity() // Ends the current activity and all parent activities
        }
    }
}

