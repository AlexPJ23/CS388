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


class MainActivity : AppCompatActivity() {
    val wordToGuess : String = FourLetterWordList.getRandomFourLetterWord().uppercase()

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
        val button = findViewById<Button>(R.id.guessBtn)
        val inputText = findViewById<EditText>(R.id.inputText)
        var iterator  = 0


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
        button.setOnClickListener {
            this.currentFocus?.let { view ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }
            if ( iterator < attempts.size) {
                Toast.makeText(it.context,"buttonCLicked", Toast.LENGTH_SHORT).show()
                val userGuess = inputText.text.toString().uppercase()
                inputText.text.clear()
                val result = checkGuess(userGuess)
                Log.d("Logging",userGuess)
                attempts[iterator].text = "Guess ${iterator + 1}: $userGuess"
                checks[iterator].text = "Check: $result"
                iterator++
                if(iterator == attempts.size){
                    button.isEnabled = false;
                    Toast.makeText(it.context,"Exhausted all attempts. Game Over!",Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(it.context,"Exhausted all attempts. Game Over!",Toast.LENGTH_SHORT)
            }
        }

    }
}