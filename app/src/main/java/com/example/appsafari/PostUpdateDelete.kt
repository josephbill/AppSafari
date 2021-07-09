package com.example.appsafari

import android.app.Activity
import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_post_update_delete.*
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class PostUpdateDelete : AppCompatActivity() {
    private var imageUri: Uri? = null //uri can pick the actual path of image
    //variables to store users input
    var texta: String = ""
    var textb: String = ""
    var userId = "2"
    private var mRequestQueue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_update_delete)

        image1.setOnClickListener{
            pickImage()
        }
        postData.setOnClickListener{
            posttoApi()
        }
        
        updateData.setOnClickListener { 
            updateApi()
        }

        deleteData.setOnClickListener {
            deleteToApi(userId)
        }
        
    }

    private fun deleteToApi(userId: String) {
         var url = "https://postman-echo.com/delete?" + userId
        val request = StringRequest(Request.Method.DELETE,url,{ response ->
            try {
                Log.d("message","Response : $response")
                Toast.makeText(applicationContext, "Delete successfull",Toast.LENGTH_LONG)
                        .show()
            } catch (e: JSONException){
                Log.d("message","Exception : $e")
            }

        }) {
            Log.d("message","Error : $it")
            Toast.makeText(applicationContext, "Error occurred",Toast.LENGTH_LONG).show()
        }
        // Volley request policy, only one time request to avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                // 0 means no retry
                0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
                1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        //adding request to volley
        VolleySingelton.getInstance(this).addToRequestQueue(request)
    }

    private fun updateApi() {
        //capture user input
        texta = textaa.text.toString()
        textb = textba.text.toString()
        //update using api
        usingApiUpdate(texta,textb,userId)
    }

    private fun usingApiUpdate(texta: String, textb: String, userId: String) {
        var url = "https://postman-echo.com/put?" + userId
        val params = HashMap<String,String>()
        params["foo1"] = texta
        params["foo2"] = textb
        val jsonObject = JSONObject(params as Map<*,*>)
        val request = JsonObjectRequest(Request.Method.PUT,url,jsonObject,
                     Response.Listener {
                         try{
                             Log.d("message","Response: $it")
                             Toast.makeText(applicationContext,"Update successful",Toast.LENGTH_LONG)
                                     .show()
                         } catch (e: JSONException){
                             e.printStackTrace()
                         }
                     }, Response.ErrorListener {
                              Log.d("message","Error: $it")
                             Toast.makeText(applicationContext,"Error, try again",Toast.LENGTH_LONG)
                             .show()
                   })

        // Volley request policy, only one time request to avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                // 0 means no retry
                0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
                1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        //adding request to volley
        VolleySingelton.getInstance(this).addToRequestQueue(request)
         }

    private fun pickImage() {
        //using image picker
        ImagePicker.with(this)
            .crop()
            .compress(1024) //image size will be less than one mb
            .maxResultSize(1080,1080) //dimesions of image
            .start()
    }

    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            //picking path of image selected
            imageUri = data?.data!!
            //set the image selected to the imageview container
            image1.setImageURI(imageUri)
        } else if (resultCode == ImagePicker.RESULT_ERROR){
             Toast.makeText(this,ImagePicker.getError(data),
                  Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this,"Task cancelled",
                Toast.LENGTH_LONG).show()
        }
    }
    private fun posttoApi() {
        //capture user input
        texta = textaa.text.toString()
        textb = textba.text.toString()
        //run the actual method to submit to api
        submitToApi(texta,textb,imageUri)
    }

    private fun submitToApi(texta: String, textb: String, imageUri: Uri?) {
           //give url to post to
          val url = "https://postman-echo.com/post"
        //using hashmap to post my details
        val params = HashMap<String,String>()
        params["foo1"] = texta
        params["foo2"] = textb
        val jsonObject  = JSONObject(params as Map<*,*>)

        val request = JsonObjectRequest(Request.Method.POST,url,jsonObject,
            Response.Listener {
                //capture success
                              try {
                                 Log.d("message","Response: $it")
                                  Toast.makeText(applicationContext,
                                      "Data posted successfully",
                                      Toast.LENGTH_LONG).show()

                              } catch (e: Exception){
                                  Log.d("message","Exception: $e")
                              }
            }, Response.ErrorListener {
                Log.d("message","Error: $it")
                Toast.makeText(applicationContext,
                    "Error occurred " + it.toString(),
                    Toast.LENGTH_LONG).show()
            })

           //volley request policy ,
          request.retryPolicy = DefaultRetryPolicy(
                  DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                  //0 means no retry
               0, //max number of retrys will be the default from volley
          1f //timeout ,
          )

        //add request to volley using singleton class
        VolleySingelton.getInstance(this).addToRequestQueue(request)


}

    }
















