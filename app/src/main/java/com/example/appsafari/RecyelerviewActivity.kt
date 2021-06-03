package com.example.appsafari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recyelerview.*

class RecyelerviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyelerview)
        //setting the list items for my recyclerview
        val listRecycler = generateStaticList()
        //connect our widget to the adapter
        recylerView.adapter = RecyclerAdapter(this,listRecycler)
        //setting layout for recycled itesm
        recylerView.layoutManager = LinearLayoutManager(this)
        //state that the recyclerview has a fixed item size
        recylerView.setHasFixedSize(true)

    }

    private fun generateStaticList() : List<RecyclerModel> {
        //arraylist to add data
        val listItems = ArrayList<RecyclerModel>()
        //add to data model
        listItems.add(
            RecyclerModel(
            R.drawable.ic_baseline_ac_unit_24,
            "Joseph",
        "25",
        "8117"))
        listItems.add(RecyclerModel(
            R.drawable.ic_launcher_background,
            "Mary",
            "20",
            "8107"))
        listItems.add(RecyclerModel(
            R.drawable.ic_launcher_foreground,
            "Keith",
            "25",
            "8909"))
        //return our list
        return listItems
    }

}