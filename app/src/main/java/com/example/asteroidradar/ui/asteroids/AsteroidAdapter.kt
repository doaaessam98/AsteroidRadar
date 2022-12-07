package com.example.asteroidradar.ui.asteroids

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.DifferCallback
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.asteroidradar.databinding.ItemAsteroidBinding
import com.example.asteroidradar.models.Asteroid
import com.example.asteroidradar.models.AsteroidEntity

class AsteroidAdapter(private val onclick:(Asteroid)->Unit) :ListAdapter<Asteroid,AsteroidAdapter.AsteroidViewHolder>(AsteroidDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        val binding = ItemAsteroidBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AsteroidViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val asteroidItem = getItem(position)
        holder.bind(asteroidItem, onclick)
    }

    class AsteroidViewHolder(private val binding: ItemAsteroidBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: Asteroid, onclick:(Asteroid)->Unit) {
            binding.asteroid = item
            binding.root.setOnClickListener { onclick.invoke(item) }
            binding.executePendingBindings()
        }

    }

   class AsteroidDiffCallback:DiffUtil.ItemCallback<Asteroid>(){
       override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
           return oldItem.id == newItem.id

       }

       override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
           return oldItem == newItem
       }
   }
}