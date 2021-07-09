package com.example.appsafari

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.covid_item.view.*

class CovidAdapter(private val context: Context,
                   private val covidList: List<CovidModel>)
            : RecyclerView.Adapter<CovidAdapter.CovidViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CovidAdapter.CovidViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(
            R.layout.covid_item,parent,false
        )
        return CovidAdapter.CovidViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: CovidAdapter.CovidViewHolder, position: Int) {
        //tag to ref the position based off the model
        val items = covidList[position]
        //bind or set data
        holder.c_name.text = "Country: " + items.countryName
        holder.c_newDeath.text = "New Death: " + items.countryNewDeath
        holder.c_totalDeath.text = "Total death: " + items.countryTotalDeath
        holder.c_newRec.text = "New recoveries: " + items.countryNewRec
        holder.c_totalRec.text = "Total recoveries: " + items.countryTotalRec
        holder.c_newcases.text = "New cases: " + items.countryNewCases
        holder.c_totalcase.text = "Total cases: " + items.countryTotalCases
        holder.infoDate.text = "Last update: " + items.updateDate
    }

    override fun getItemCount()  = covidList.size
    //define the vieholder
    class CovidViewHolder(view: View) : RecyclerView.ViewHolder(view){
       val c_name: TextView = view.coronaCountry
       val c_newcases: TextView = view.coronaNewCases
       val c_totalcase: TextView = view.coronaTotalCases
       val c_newRec: TextView = view.coronaNewRec
       val c_totalRec: TextView = view.coronaTotalRec
       val c_newDeath : TextView = view.coronaNewDeath
       val c_totalDeath: TextView = view.coronaTotalDeath
       val infoDate : TextView = view.infoDate
    }
}






























