package com.example.appsafari

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class FragmentB : Fragment() {

    //tags
    lateinit var texts: TextView
    //variable to store shared text
    var messageShared: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_b, container, false)
        texts = view.findViewById(R.id.sharedText)
        //using arguments function to get shared info from bundle
        messageShared = arguments?.getString("shareData").toString()
        //set the text shared to textview
        texts.text = messageShared
        return view
    }









}