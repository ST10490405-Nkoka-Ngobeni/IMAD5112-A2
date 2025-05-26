package com.example.flashcardsimad5112a2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Easy : AppCompatActivity() {

    // Declare UI components
    private lateinit var txtquestion: TextView
    private lateinit var btntrue: Button
    private lateinit var btnfalse: Button
    private lateinit var txtfeedback: TextView
    private lateinit var txtscore: TextView
    private lateinit var btnNext: Button

    // Track current question index and user score
    private var currentIndex = 0
    private var score = 0

    // List of flashcards (questions and answers)
    private val flashcards = arrayListOf(
        Flashcard("Nelson Mandela was the president in 1994.", true),
        Flashcard("The first Europeans to arrive in South Africa were the Spanish.", false),
        Flashcard("The national flower of South Africa is the King Protea.", true),
        Flashcard("The Cold War ended in 1991.", true),
        Flashcard("The Kruger National Park is located in the Eastern Cape province.", false),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_easy)

        // Link UI components with their XML IDs
        txtquestion = findViewById(R.id.txtquestion)
        btntrue = findViewById(R.id.btntrue)
        btnfalse = findViewById(R.id.btnfalse)
        txtfeedback = findViewById(R.id.txtfeedback)
        txtscore = findViewById(R.id.txtscore)
        btnNext = findViewById(R.id.btnnext)

        // Hide the Next button at the start
        btnNext.visibility = View.GONE

        // Set listeners for True/False buttons
        btntrue.setOnClickListener { checkAnswer(true) }
        btnfalse.setOnClickListener { checkAnswer(false) }

        // Next button opens the ScoreActivity and passes data
        btnNext.setOnClickListener {
            Toast.makeText(this, "Next clicked!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("SCORE", score)
            intent.putExtra("TOTAL", flashcards.size)
            intent.putExtra("FLASHCARDS", flashcards)
            startActivity(intent)
            finish() // close the current activity
        }

        // Display the first question
        displayQuestion()
    }

    // Displays a question or the end screen if all questions are answered
    private fun displayQuestion() {
        if (currentIndex < flashcards.size) {
            val currentFlashcard = flashcards[currentIndex]
            txtquestion.text = currentFlashcard.statement
            txtfeedback.text = ""
            txtscore.text = "Score: $score"
            btnNext.visibility = View.GONE // Hide Next button during questions
        } else {
            // End of quiz: show final score and reveal Next button
            txtquestion.text = "You've completed all questions!"
            txtfeedback.text = "Your final score is $score/${flashcards.size}"
            txtscore.text = ""
            btntrue.visibility = View.GONE
            btnfalse.visibility = View.GONE
            btnNext.visibility = View.VISIBLE
        }
    }

    // Checks if the user's answer is correct, updates score, and loads next question
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = flashcards[currentIndex].isTrue
        if (userAnswer == correctAnswer) {
            txtfeedback.text = "Correct!!"
            score++
        } else {
            txtfeedback.text = "Incorrect!!"
        }

        currentIndex++ // Move to next question

        // Delay before showing next question to allow user to read feedback
        lifecycleScope.launch {
            delay(1000)
            displayQuestion()
        }
    }

    // Flashcard data model marked Serializable for Intent transfer
    data class Flashcard(val statement: String, val isTrue: Boolean) : java.io.Serializable
}

// Placeholder method (not required for Serializable extras but included from previous version)
private fun Intent.putParcelableArrayListExtra(
    string: kotlin.String,
    flashcards: kotlin.collections.ArrayList<com.example.flashcardsimad5112a2.Easy.Flashcard>
) {
    // This is a stub and isn't actually needed since Serializable is used.
}



/*package com.example.flashcardsimad5112a2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Easy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_easy)



        var txtquestion = findViewById<TextView>(R.id.txtquestion)
        var btntrue = findViewById<Button>(R.id.btntrue)
        var btnfalse = findViewById<Button>(R.id.btnfalse)
        var txtfeedback = findViewById<TextView>(R.id.txtfeedback)
        var txtscore = findViewById<TextView>(R.id.txtscore)

       /* lateinit var questionTextView: TextView
        lateinit var trueButton: Button
        lateinit var falseButton: Button
        lateinit var feedbackTextView: TextView
        lateinit var scoreTextView: TextView*/

        data class Flashcard(val statement: String, val isTrue: Boolean)

        val flashcards = arrayListOf(
            Flashcard("Nelson Mandela was the president in 1994.", true),
            Flashcard("The Great Wall of China is visible from space.", false),
            Flashcard("The Roman Empire fell in 476 AD.", true),
            Flashcard("The Cold War ended in 1991.", true),
            Flashcard("The Berlin Wall fell in 1989.", true)
        )



        var currentIndex = 0
        var score = 0



        var answerClickListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.btntrue -> checkAnswer(true)
                R.id.btnfalse -> checkAnswer(false)
            }
        }

        btntrue.setOnClickListener(answerClickListener)
        btnfalse.setOnClickListener(answerClickListener)


             fun displayQuestion() {
                if (currentIndex < flashcards.size) {
                val currentFlashcard = flashcards[currentIndex]
                txtquestion.text = currentFlashcard.statement
                txtfeedback.text = ""
                txtscore.text = "Score: $score"
                } else {
                val intent = Intent(this, Score::class.java)
                intent.putExtra("SCORE", score)
                intent.putExtra("TOTAL", flashcards.size)
                intent.putExtra("FLASHCARDS", ArrayList(flashcards))
                startActivity(intent)
                finish()
            }
        }
        @Suppress("unused")
        //fun checkAnswer(userAnswer: Boolean) {
        val checkAnswer: (Boolean) -> Unit = { userAnswer ->
            val correctAnswer = flashcards[currentIndex].isTrue
            if (userAnswer == correctAnswer) {
                txtfeedback.text = "Correct!"
                score++
            } else {
                txtfeedback.text = "Incorrect"
            }
            currentIndex++
            lifecycleScope.launch {
                delay(1000)
                displayQuestion()
            }
        }
    }
}*/