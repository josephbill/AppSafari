package com.example.appsafari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_intent_sharing.*

class IntentSharingActivity : AppCompatActivity() {
    //declare variables
    var textSample1: String = ""
    var textEdit: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_sharing)

        //click listener
        button.setOnClickListener {
            //capture user data
            textEdit = editData.text.toString()
            //1st use case of intent , start an activity
            val intentSharing = Intent(this,IntentSharingActivityB::class.java)
           //intent to share text
            intentSharing.putExtra("editTextData",textEdit)
            intentSharing.putExtra("newData","this is new data")
            intentSharing.putExtra("digit","5")
            //intent to share image
            intentSharing.putExtra("logo",R.drawable.ic_baseline_ac_unit_24)
            startActivity(intentSharing)
        }

    }
}