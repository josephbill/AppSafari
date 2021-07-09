package com.example.appsafari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_shared_preference.*

class SharedPreferenceActivity : AppCompatActivity() {
    //reference to our interface and preference manager class
    // here we lazy load our preference manager class: will only only be loaded at
    //runtime when its operation is met
    private val preferenceHelper: IPreferenceHelper
    by lazy { PreferenceManager(applicationContext) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)

            textView.text = "API KEY IS -> ${preferenceHelper.getApiKey()} " +
                    " \n User id -> ${preferenceHelper.getUserId()}"

        button.setOnClickListener {
              //capture text and use Preference manager to save the details SharedPreferences
            preferenceHelper.setApiKey(editText.text.toString())
            preferenceHelper.setUserId(editText2.text.toString())

            //display text
            textView.text = "API KEY IS -> ${preferenceHelper.getApiKey()} " +
                    " \n User id -> ${preferenceHelper.getUserId()}"
        }
    }
}











