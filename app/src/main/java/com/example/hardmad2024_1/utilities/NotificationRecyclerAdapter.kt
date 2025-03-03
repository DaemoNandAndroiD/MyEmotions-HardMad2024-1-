package com.example.hardmad2024_1.utilities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hardmad2024_1.R

class NotificationRecyclerAdapter():RecyclerView.Adapter<NotificationRecyclerAdapter.NotificationViewHolder>() {
    private val items:MutableList<String> = mutableListOf()

    class NotificationViewHolder(view: View):RecyclerView.ViewHolder(view){
        val textView = view.findViewById<TextView>(R.id.time)
        val button = view.findViewById<ImageButton>(R.id.delete_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notification_item, parent, false)
        return NotificationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.textView.text = items[position]
        holder.button.setOnClickListener {
            deleteItem(position)
        }
    }

    fun addItem(time:String){
        items.add(time)
        notifyItemChanged(items.size - 1)
    }

    fun deleteItem(position: Int){
        items.removeAt(position)
        notifyDataSetChanged()
    }
}