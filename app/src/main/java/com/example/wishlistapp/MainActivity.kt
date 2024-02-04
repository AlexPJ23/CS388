package com.example.wishlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val itemRv = findViewById<RecyclerView>(R.id.recyclerView)
        val items : MutableList<Item> = ArrayList<Item>()
        val adapter : ItemAdapter = ItemAdapter(items)
        itemRv.adapter = adapter
        itemRv.layoutManager = LinearLayoutManager(this)
        findViewById<Button>(R.id.button2).setOnClickListener {

            adapter.notifyDataSetChanged()
        }

    }
}