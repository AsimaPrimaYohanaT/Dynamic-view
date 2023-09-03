package com.example.dynamicviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicviews.databinding.ActivitySoundingBinding


class activity_sounding : AppCompatActivity() {

    private lateinit var binding: ActivitySoundingBinding
    var soundingList: ArrayList<Sounding> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySoundingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerSounding.layoutManager = layoutManager

        soundingList = (intent.getSerializableExtra("list") as ArrayList<Sounding>)

        binding.recyclerSounding.adapter = SoundingAdapter(soundingList)
    }
}