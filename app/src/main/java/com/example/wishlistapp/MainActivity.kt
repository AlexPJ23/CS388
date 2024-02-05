package com.example.wishlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val itemRv = findViewById<RecyclerView>(R.id.recyclerView)
        val items : MutableList<Item> = ArrayList<Item>()
        val adapter : ItemAdapter = ItemAdapter(items)
        val data = arrayListOf<EditText>(
            findViewById(R.id.itemName),
            findViewById(R.id.price),
            findViewById(R.id.source)
        )
        itemRv.adapter = adapter
        itemRv.layoutManager = LinearLayoutManager(this)
        findViewById<Button>(R.id.button2).setOnClickListener {
            var new_item : Item = Item(data[0].text.toString(),data[1].text.toString().toFloat(),data[2].text.toString())
            items.add(new_item)
            adapter.notifyDataSetChanged()
        }

    }
}