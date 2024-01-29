package com.example.wordleproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import FourLetterWordList
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import android.content.Context
import android.view.View
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {
    var wordToGuess : String = FourLetterWordList.getRandomFourLetterWord().uppercase()

//     * Parameters / Fields:
//     *   wordToGuess : String - the target word the user is trying to guess
//     *   guess : String - what the user entered as their guess
//     *
//     * Returns a String of 'O', '+', and 'X', where:
//     *   'O' represents the right letter in the right place
//     *   '+' represents the right letter in the wrong place
//     *   'X' represents a letter not in the target word
//     */
    private fun checkGuess(guess: String): String {
        var result = ""
        if (guess.length < 4 || wordToGuess.length < 4) {
            return "Invalid guess or wordToGuess"
        }

        for (i in 0 until 4) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            } else if (guess[i] in wordToGuess) {
                result += "+"
            } else {
                result += "X"
            }
        }
        return result
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val guessBtn = findViewById<Button>(R.id.guessBtn)
        val resetBtn = findViewById<Button>(R.id.resetBtn)
        val inputText = findViewById<EditText>(R.id.inputText)
        var iterator  = 0
        val secretBox = findViewById<TextView>(R.id.secretWord)
        secretBox.text= wordToGuess;

        val attempts = listOf<TextView>(
            findViewById(R.id.GuessAttempt1),
            findViewById(R.id.GuessAttempt2),
            findViewById(R.id.GuessAttempt3)
        )
        val checks = listOf<TextView>(
            findViewById(R.id.GuessCheck1),
            findViewById(R.id.GuessCheck2),
            findViewById(R.id.GuessCheck3)
        )
        guessBtn.setOnClickListener {
            this.currentFocus?.let { view ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }
            if ( iterator < attempts.size) {
                Toast.makeText(it.context,"buttonCLicked", Toast.LENGTH_SHORT).show()
                val userGuess = inputText.text.toString().uppercase()
                inputText.text.clear()
                val result = checkGuess(userGuess)
                attempts[iterator].text = "Guess ${iterator + 1}: $userGuess"
                checks[iterator].text = "Check: $result"
                if( userGuess == wordToGuess ){
                    guessBtn.isEnabled = false;
                    Toast.makeText(it.context, "Congratulations You answered the correct word",Toast.LENGTH_SHORT).show()
                    secretBox.visibility = View.VISIBLE;
                    resetBtn.visibility = View.VISIBLE
                }
                iterator++
                if(iterator == attempts.size){
                    guessBtn.isEnabled = false;
                    Toast.makeText(it.context,"Exhausted all attempts. Game Over!",Toast.LENGTH_SHORT).show()
                    secretBox.visibility = View.VISIBLE;
                    resetBtn.visibility = View.VISIBLE
                }
            }
        }
        resetBtn.setOnClickListener {
            wordToGuess = FourLetterWordList.getRandomFourLetterWord().uppercase()
            iterator = 0
            inputText.text.clear()
            guessBtn.isEnabled = true
            resetBtn.visibility = View.GONE
            secretBox.visibility = View.GONE
            secretBox.text= wordToGuess;
            attempts.forEach { it.text = "" }
            checks.forEach { it.text = "" }
            Toast.makeText(applicationContext, "Game has been reset", Toast.LENGTH_SHORT).show()
        }

    }
}