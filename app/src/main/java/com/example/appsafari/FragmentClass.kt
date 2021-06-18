package com.example.appsafari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fragment_class.*

class FragmentClass : AppCompatActivity(), Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_class)

        //load up a default fragment so that the view
        //is not empty
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer
                ,FragmentA()).commit()

        //setting the switch
//        switchFrag.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragmentContainer
//                    ,FragmentB()).commit()
//        }
    }

    override fun passData(text: String) {
        //intialize ref to bundlw clASS
        val bundle = Bundle()
        //saving the info to be shared
        bundle.putString("shareData",text)
        //tag for transition of fragment
        val transition = this.supportFragmentManager.beginTransaction()
        //set tag for second frag
        val fragB = FragmentB()
        //using arguments function to share data na also begin transition
        fragB.arguments = bundle
        //begin transition
        transition.replace(R.id.fragmentContainer,fragB)
        transition.commit()
    }


















}