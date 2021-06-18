package com.example.appsafari

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText

class FragmentModelA : Fragment() {
      //tag for our viewmodel class
    lateinit var model: SharedViewModel
    //tag for our edittext and btn
    lateinit var textData: TextInputEditText
    lateinit var btnShare: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_model_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //view ref
        textData = view.findViewById(R.id.textShared)
        btnShare = view.findViewById(R.id.button4)
        //create an instance of the shared viewmodel class'
        model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)


        btnShare.setOnClickListener {
            //capture user input
            val userInput = textData.text.toString()
            model.sendMessage(userInput)

        }

    }


















}