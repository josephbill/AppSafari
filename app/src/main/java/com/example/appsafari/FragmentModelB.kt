package com.example.appsafari

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class FragmentModelB : Fragment() {
    //create ref to shared view model and textview
    lateinit var model: SharedViewModel
    lateinit var textSample: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_model_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //create the instance of the shared viewmodel class
        model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        //view ref
        textSample = view.findViewById(R.id.sharedText)
        //using model to observe change in my Mutable Live Data
        model.message.observe(viewLifecycleOwner, Observer {
                 textSample.text = it
        })
    }
























}