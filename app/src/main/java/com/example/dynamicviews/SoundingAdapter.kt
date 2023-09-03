package com.example.dynamicviews

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicviews.databinding.ItemSoundingBinding


class SoundingAdapter(private val soundingList: ArrayList<Sounding>) :
    RecyclerView.Adapter<SoundingAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemSoundingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return soundingList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = soundingList[position]
        if (data != null) {
            holder.bind(data)
        }
    }

    class MyViewHolder(private val binding: ItemSoundingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Sounding) {
            binding.levelSounding.text = data.levelSounding
            binding.nomorTanki.text = data.nomorTanki
        }
    }
}