package com.example.a7minuteworkoutapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minuteworkoutapplication.databinding.ActivityEndPageBinding

class EndPage : AppCompatActivity() {

    private var binding:ActivityEndPageBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding=ActivityEndPageBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarFinishActivity)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarFinishActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
        //END



        binding?.btnFinish?.setOnClickListener {
            finish()
        }


    }
}