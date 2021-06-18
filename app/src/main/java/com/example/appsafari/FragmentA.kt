package com.example.appsafari

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText


class FragmentA : Fragment() {
      //tag for interface
    lateinit var communicator: Communicator
    //tag for edit text // tag for button
   lateinit var editText: TextInputEditText
   lateinit var btnShare: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view = inflater.inflate(R.layout.fragment_a,container,false)
         editText = view.findViewById(R.id.textShared)
        btnShare = view.findViewById(R.id.button4)

        //intialize the interface
        communicator = activity as Communicator

        //share our details
        btnShare.setOnClickListener {
            val sharedText = editText.text.toString()
            communicator.passData(sharedText)
        }
         return view


    }





















}