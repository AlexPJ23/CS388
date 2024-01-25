package com.example.simplecounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.addBtn)
        val textView = findViewById<TextView>(R.id.textView)
        button.setOnClickListener{
            counter++
            textView.text = counter.toString()
            Toast.makeText(it.context, "Clicked Button!", Toast.LENGTH_SHORT)
        }
    }

}