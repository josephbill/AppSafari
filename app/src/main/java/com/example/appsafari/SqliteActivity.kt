package com.example.appsafari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sqlite.*

class SqliteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)
    }

    fun saveData(view: View){
        //capture user data
        val id = editId.text.toString()
        val name = editName.text.toString()
        val email = editEmail.text.toString()
        //instance of the Databasehandler class
        val databaseHelper = DatabaseHelper(this)
        //validation
        if (id.trim() != "" && name.trim() != "" && email.trim() != ""){
            //if its not equal to blank we can write to db
            val status = databaseHelper.addUsers(SqiliteModel(Integer.valueOf(id),
                                                 name,email))
            if (status > -1){
                Toast.makeText(applicationContext,"Record saved",
                    Toast.LENGTH_LONG).show()
                //clear inputs
                editId.text?.clear()
                editName.text?.clear()
                editEmail.text?.clear()
            } else {
                Toast.makeText(applicationContext,"Something went wrong ,try again",
                        Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(applicationContext,"Fields cannot be empty",
                Toast.LENGTH_LONG).show()
        }

    }

    //view data
    fun viewData(view: View){
         //define instance of the database helper class
        val databaseHelper = DatabaseHelper(this)
        //make ref to the read data method
        val viewdata: List<SqiliteModel> = databaseHelper.readData()
        //define array variables to store each record detail
        val arrayId = Array<String>(viewdata.size){"0"}
        val arrayName = Array<String>(viewdata.size){"null"}
        val arrayEmail = Array<String>(viewdata.size){"null"}
        //looping our records to save in the variables above
        var index = 0
        for (e in viewdata){
            arrayId[index] = e.userId.toString()
            arrayName[index] = e.userName
            arrayEmail[index] = e.userEmail
            index++
        }
        //create details for adapter and also set the adapter to the listview
        val myadapter = SqliteAdapter(this,arrayId,arrayName,arrayEmail)
        listItems.adapter = myadapter

    }
}

















