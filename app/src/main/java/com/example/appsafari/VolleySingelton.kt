package com.example.appsafari

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleySingelton constructor(context: Context) {
       companion object{
           @Volatile
           private var INSTANCE: VolleySingelton? = null
           fun getInstance(context: Context) =
                   INSTANCE ?: synchronized(this){
                       INSTANCE ?: VolleySingelton(context).also {
                           INSTANCE = it
                       }
                   }
       }
       //declare the requestQueue
      private val requestQueue: RequestQueue by lazy {
          Volley.newRequestQueue(context.applicationContext)
       }
     fun <T> addToRequestQueue(req: Request<T>){
         requestQueue.add(req)
     }
}


















