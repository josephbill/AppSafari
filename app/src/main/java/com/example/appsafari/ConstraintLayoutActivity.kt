package com.example.appsafari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_constraint_layout.*

class ConstraintLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout)

        btnb.setOnClickListener {
            //transition
            val intent = Intent(this@ConstraintLayoutActivity,MainActivity::class.java)
            startActivity(intent)
        }
    }
}