package com.example.appsafari

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class SqliteAdapter(private val context: Activity, private val id: Array<String>,
                    private val name: Array<String>, private val email:Array<String>)
    : ArrayAdapter<String>(context,R.layout.custom_list,name){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
             val inflater = context.layoutInflater
             val rowview = inflater.inflate(R.layout.custom_list,null,true)
             //view identification
            val textId: TextView = rowview.findViewById(R.id.userId)
            val textName: TextView = rowview.findViewById(R.id.userName)
            val textEmail: TextView = rowview.findViewById(R.id.userEmail)
           //set the data according to position
           textId.text = "ID: ${id[position]}"
           textName.text = "Name: ${name[position]}"
           textEmail.text = "Email: ${email[position]}"

        return rowview

    }


}




















































