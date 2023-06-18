package com.example.a7minuteworkoutapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.example.a7minuteworkoutapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.flstart?.setOnClickListener {
            //Toast.makeText(this,"here we go dude",Toast.LENGTH_SHORT).show()
            val intent=Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
        }

        binding?.flBMI?.setOnClickListener {
            //Toast.makeText(this,"here we go dude",Toast.LENGTH_SHORT).show()
            val intent=Intent(this,BMIActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding=null
     }
}