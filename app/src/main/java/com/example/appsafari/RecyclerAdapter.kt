package com.example.appsafari

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_item.view.*

class RecyclerAdapter
    (private val context: Context, private val itemlist: List<RecyclerModel>) :
     RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>()
{


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapter.RecyclerViewHolder {
         val inflater = LayoutInflater.from(parent.context).inflate(
             R.layout.custom_item,parent,false
         )
        return RecyclerViewHolder(inflater)
    }

    override fun getItemCount() = itemlist.size

    override fun onBindViewHolder(holder: RecyclerAdapter.RecyclerViewHolder, position: Int) {
            //create a var that will ref the item position
            val items = itemlist[position]

           //set data
        holder.imageView.setImageResource(items.studentImage)
        holder.textname.text = items.studentName
        holder.textAdm.text = items.studentAdm
        holder.textage.text = items.studentAge


    }

//    declare view holder
    class RecyclerViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView){
         //declaring variables to ref the views in my recycled item
         val imageView: ImageView =  itemView.studentImage
         val textname: TextView = itemView.studentName
         val textage: TextView = itemView.studentAge
         val textAdm: TextView = itemView.studentAdm
    }


}