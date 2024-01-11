package com.example.retrofitexample.network

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.retrofitexample.R

class MainAdapter(val charactersList: List<Character>):RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(private val itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindData(character: Character){
            val name=itemView.findViewById<TextView>(R.id.name)
            val image=itemView.findViewById<ImageView>(R.id.image)

            name.text=character.name
            // we are going to load the image and in this case we are using coil
            image.load(character.image){
                transformations(CircleCropTransformation())
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        // this will inflate the rv item for every single items.
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false))
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(charactersList[position]) // get the character on that positon and bind the holder.
    }
}