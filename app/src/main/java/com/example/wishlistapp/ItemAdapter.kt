package com.example.wishlistapp

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import android.view.View
import android.view.LayoutInflater

class ItemAdapter (private val items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val item : TextView
        val price: TextView
        val store: TextView

        init {

            item = itemView.findViewById(R.id.item)
            price = itemView.findViewById(R.id.price)
            store = itemView.findViewById(R.id.store)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        val context =  parent.context
        val inflator = LayoutInflater.from(context)
        val contactVeiw = inflator.inflate(R.layout.item_layout,parent,false)
        return ViewHolder(contactVeiw)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        val items = items[position]
        holder.item.text = items.name
        holder.price.text = items.price.toString()
        holder.store.text = items.url

    }

    override fun getItemCount(): Int {
        return items.size
    }
}