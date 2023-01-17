package com.wit.voguely.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wit.voguely.R
import com.wit.voguely.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }
}