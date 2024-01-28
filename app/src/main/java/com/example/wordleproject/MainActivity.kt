package com.example.wordleproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import FourLetterWordList
class MainActivity : AppCompatActivity() {

    val wordToGuess : String = FourLetterWordList.getRandomFourLetterWord()

//     * Parameters / Fields:
//     *   wordToGuess : String - the target word the user is trying to guess
//     *   guess : String - what the user entered as their guess
//     *
//     * Returns a String of 'O', '+', and 'X', where:
//     *   'O' represents the right letter in the right place
//     *   '+' represents the right letter in the wrong place
//     *   'X' represents a letter not in the target word
//     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.guessBtn)
        val inputText = findViewById<EditText>(R.id.inputText)
        var iterator : Int = 0

        val attempts = listOf<TextView>(
            findViewById<TextView>(R.id.GuessAttempt1),
            findViewById<TextView>(R.id.GuessAttempt2),
            findViewById<TextView>(R.id.GuessAttempt3)
        )
        val checks = listOf<TextView>(
            findViewById<TextView>(R.id.GuessCheck1),
            findViewById<TextView>(R.id.GuessCheck2),
            findViewById<TextView>(R.id.GuessCheck3)
        )
        button.setOnClickListener {
            var userGuess : String = inputText.text.toString()
            inputText.text.clear()
            var result : String  = checkGuess(userGuess);
            attempts[iterator].text = " Guess #${iterator+1} ${userGuess}";
            checks[iterator].text = "Guess #${iterator + 1} Check ${result}"
            iterator++

        }

    }
}