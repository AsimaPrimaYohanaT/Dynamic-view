package com.example.dynamicviews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatSpinner
import com.example.dynamicviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private val teamList = arrayListOf("DB (S)", "DB (P)", "AFT (S)", "AFT (P)")
    private val soundingList = arrayListOf<Sounding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener(this)
        binding.buttonSubmitList.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_add -> addView()
            R.id.button_submit_list-> {
                if (checkIfValidAndRead()) {
                    val intent = Intent(this@MainActivity, activity_sounding::class.java)
                    intent.putExtra("list", soundingList)
                    startActivity(intent)
                }
            }
        }
    }

    private fun checkIfValidAndRead(): Boolean {
        soundingList.clear()
        var result = true

        for (i in 0 until binding.layoutList.childCount) {
            val soundingView = binding.layoutList.getChildAt(i)

            val edSounding1 = soundingView.findViewById<EditText>(R.id.ed_sounding_1)
            val edSounding2 = soundingView.findViewById<EditText>(R.id.ed_sounding_2)
            val edSounding3 = soundingView.findViewById<EditText>(R.id.ed_sounding_3)
            val edSounding4 = soundingView.findViewById<EditText>(R.id.ed_sounding_4)
            val edSounding5 = soundingView.findViewById<EditText>(R.id.ed_sounding_5)

            val spinnerTeam = soundingView.findViewById<AppCompatSpinner>(R.id.spinner_team)

            val sounding = Sounding()

            if(edSounding1.text.isEmpty()|| edSounding2.text.isEmpty() || edSounding3.text.isEmpty() ){
                Toast.makeText(this, "Add Sounding First!", Toast.LENGTH_SHORT).show()
                break
            }else if (edSounding1.text.toString() != null && edSounding2.text.toString() != null && edSounding3.text.toString() != null ) {
                val sounding1 = edSounding1.text.toString().toDouble()
                val sounding2 = edSounding2.text.toString().toDouble()
                val sounding3 = edSounding3.text.toString().toDouble()
                val sum = sounding1 + sounding2 + sounding3
                val average = sum /3
                sounding.levelSounding = average.toString()
            } else {
                result = false
                break
            }

            if (spinnerTeam.selectedItemPosition != 0) {
                sounding.nomorTanki = teamList[spinnerTeam.selectedItemPosition]
            } else {
                result = false
                break
            }

            soundingList.add(sounding)
        }

        if (soundingList.isEmpty()) {
            result = false
            Toast.makeText(this, "Add Sounding First!", Toast.LENGTH_SHORT).show()
        } else if (!result) {
            Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show()
        }

        return result
    }

    private fun addView() {
        val cricketerView = layoutInflater.inflate(R.layout.row_add_sounding, null, false)
        val spinnerTeam = cricketerView.findViewById<AppCompatSpinner>(R.id.spinner_team)
        val imageClose = cricketerView.findViewById<ImageView>(R.id.image_remove)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, teamList)
        spinnerTeam.adapter = arrayAdapter

        imageClose.setOnClickListener {
            removeView(cricketerView)
        }

        binding.layoutList.addView(cricketerView)
    }

    private fun removeView(view: View) {
        binding.layoutList.removeView(view)
    }
}