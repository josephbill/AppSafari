package com.example.appsafari

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_android_networking.*
import org.json.JSONException

class AndroidNetworking : AppCompatActivity() {
    //TAG REF TO ADAPTER , MODEL , REQUESTQUEU
    private var mExampleAdapter : CovidAdapter? = null
    private var mExampleList: ArrayList<CovidModel>? = null
    private var mRequestQueue: RequestQueue? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_networking)

        recyclerCovid.layoutManager = LinearLayoutManager(this)
        recyclerCovid.setHasFixedSize(true)
        mExampleList = ArrayList()
        mRequestQueue = Volley.newRequestQueue(this)

        if (isNetworkConnected()){
            fetchDetails()
        } else {
            //sweetalert
        val errorAlert = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
            errorAlert.setTitleText("Check your internet connection")
            errorAlert.setCancelable(true)
            errorAlert.show()
        }

    }

    private fun fetchDetails() {
        val loadingDialog = SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE)
        loadingDialog.setTitleText("Loading....")
        loadingDialog.setCancelable(true)
        loadingDialog.setCanceledOnTouchOutside(false)
        loadingDialog.show()

        val url: String = "https://api.covid19api.com/summary"
        //use volley to consume details
        val request = JsonObjectRequest(Request.Method.GET,url,null,
                       Response.Listener {
                            loadingDialog.dismiss()
                           try {
                               val jsonArray = it.getJSONArray("Countries")
                               Log.d("array", "response is $jsonArray")
                               //loop the array to iterate over the objects
                               for (i in 0 until jsonArray.length()){
                                   val hit = jsonArray.getJSONObject(i)
                                   val id = hit.getString("ID")
                                   val countryName = hit.getString("Country")
                                   val countryNewCases = hit.getInt("NewConfirmed")
                                   val countryTotalCases = hit.getInt("TotalConfirmed")
                                   val countryNewRec = hit.getInt("NewRecovered")
                                   val countryTotalRec = hit.getInt("TotalRecovered")
                                   val countryNewDeath = hit.getInt("NewDeaths")
                                   val countryTotalDeath = hit.getInt("TotalDeaths")
                                   val infoDate = hit.getString("Date")
                                   //add details to array list
                                   mExampleList!!.add(
                                       CovidModel(
                                           id,
                                           countryName,
                                           countryNewCases,
                                           countryTotalCases,
                                           countryNewRec,
                                           countryTotalRec,
                                           countryNewDeath,
                                           countryTotalDeath,
                                           infoDate
                                       )
                                   )
                               }
                               //adding model to the adapter
                           mExampleAdapter = mExampleList?.let { CovidAdapter(this, it) }
                           recyclerCovid!!.adapter = mExampleAdapter
                          
                           } catch (e: JSONException){
                               e.printStackTrace()
                       }


                        }, Response.ErrorListener {
                    it -> it.printStackTrace()
                       Toast.makeText(applicationContext,it.toString(),Toast.LENGTH_LONG)
                           .show()
            })
            mRequestQueue!!.add(request)
    }

    //checking if network is connected
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun isNetworkConnected() : Boolean{
       //intializing the connectivity manager class
         val connectivityManager =
                 getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork  =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                          connectivityManager.activeNetwork
                  } else {
                    TODO("VERSION.SDK_INT < M")

                }
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null && networkCapabilities.
        hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}



















