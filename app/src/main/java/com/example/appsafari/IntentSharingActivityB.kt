package com.example.appsafari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_intent_sharing_b.*

class IntentSharingActivityB : AppCompatActivity() {
    var digit:Int = 0
    var sum: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_sharing_b)

        //pick image shared
        val bundle: Bundle? = intent.extras
        //create a ref. for the new image type
        val imagePicked: Int = bundle!!.getInt("logo")
        //set the image to our imageview
        imageView3.setImageResource(imagePicked)
        //pick text
        val sharedText: String? = intent.getStringExtra("editTextData")
        val sharedText2: String? = intent.getStringExtra("newData")
        val sharedText3: String? = intent.getStringExtra("digit")
        //type conversion
        digit = Integer.valueOf(sharedText3)

        add()

       //set text to container
        textView2.append("Value from edit text " + sharedText +
                " Value from created tag " + sharedText2 +
                   " Sum is " + sum.toString() )

    }

    fun add (){
        sum = digit + digit
    }
}