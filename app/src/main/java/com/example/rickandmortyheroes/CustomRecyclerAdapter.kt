package com.example.rickandmortyheroes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter(private val name: List<String>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameItem: TextView = itemView.findViewById(R.id.nameItem)
        val speciesItem: TextView = itemView.findViewById(R.id.speciesItem)
        val genderItem: TextView = itemView.findViewById(R.id.genderItem)
        val imageItem: ImageView = itemView.findViewById(R.id.imageItem)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameItem.text = name[position]
        // TODO change this
        holder.speciesItem.text = name[position]
        holder.genderItem.text = name[position]
    }

    override fun getItemCount(): Int {
        return name.size
    }
}