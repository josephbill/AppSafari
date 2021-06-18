package com.example.appsafari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_background_logic.*

class BackgroundLogic : AppCompatActivity() {
    //variable to store text
    var textValue: String = ""
    var intText: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background_logic)

        button3.setOnClickListener {
            textValue = textView4.text.toString()
            //convert text to be int
            intText = Integer.valueOf(textValue)
            //make ref. to the business logic
            //concept of oop of creating a new object
            val myWorker = MyWorker()
            //doubling the value
            val newValue = myWorker.doubleTheValue(intText)
            //set the new value to textview
            textView4.text = newValue.toString()
        }

    }
}