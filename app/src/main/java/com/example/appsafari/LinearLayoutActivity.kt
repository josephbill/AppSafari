package com.example.appsafari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class LinearLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //event to take me to next page
        button2.setOnClickListener {
            val intent = Intent(this@LinearLayoutActivity,RelativeLayout::class.java)
            startActivity(intent)
        }
    }
}