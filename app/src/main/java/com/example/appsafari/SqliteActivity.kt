package com.example.appsafari

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
    //update method
    fun updateData(view: View){
        //set up a dialog
        val dialogBuilder = AlertDialog.Builder(this)
        //inflate our layout to the box
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setView(dialogView)
        //reference the views inside the custom dialog
        val update_id = dialogView.findViewById<EditText>(R.id.updateId)
        val update_name = dialogView.findViewById<EditText>(R.id.updateName)
        val update_email = dialogView.findViewById<EditText>(R.id.updateEmail)
        //customize the box
        dialogBuilder.setTitle("Update data")
        dialogBuilder.setMessage("Enter an existing id " +
                ", to update a specific record")
        //set up a positive button
        dialogBuilder.setPositiveButton("Update"
            , DialogInterface.OnClickListener { dialog, which ->
                //capture user details
                val updateId = update_id.text.toString()
                val updateName = update_name.text.toString()
                val updateEmail = update_email.text.toString()
                //validate
                if (updateId.trim() != "" && updateName.trim() != ""
                    && updateEmail.trim() != ""  ){
                     //update the data
                    //instance of the database helper class
                    val databaseHelper = DatabaseHelper(this)
                    val status = databaseHelper.updateData(
                        SqiliteModel(
                        Integer.valueOf(updateId),updateName,updateEmail
                    ))
                    //monitor the process
                    if (status > -1){
                        Toast.makeText(applicationContext,"Update successful",
                            Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(applicationContext,"something went wrong",
                            Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(applicationContext,"Fields cannot be empty",
                    Toast.LENGTH_LONG).show()
                }
            })

        dialogBuilder.setNegativeButton("Cancel"
            ,DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
        dialogBuilder.setNeutralButton("Help"
            ,DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(applicationContext,"Enter id to update data"
                    ,Toast.LENGTH_LONG).show()
            })

        //create and show the dialog
        val b = dialogBuilder.create()
        b.show()

    }
    fun deleteData(view: View){
        //set up a dialog
        val dialogBuilder = AlertDialog.Builder(this)
        //inflate our layout to the box
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_dialog, null)
        dialogBuilder.setView(dialogView)
        //view identification
        val delete_id = dialogView.findViewById<EditText>(R.id.deleteId)
        //customize
        dialogBuilder.setTitle("Delete Data")
        dialogBuilder.setMessage("Enter id to delete data")
        dialogBuilder.setIcon(R.drawable.ic_baseline_ac_unit_24)
        //st buttons for builder
        dialogBuilder.setPositiveButton("Delete"
            ,DialogInterface.OnClickListener { dialog, which ->
                 //delete data
                val input_id = delete_id.text.toString()
                val databaseHelper = DatabaseHelper(this)
                if (input_id.trim() != ""){
                    val status = databaseHelper.deleteData(
                        SqiliteModel(
                            Integer.valueOf(input_id),"",""
                        ))
                    //monitor the process
                    if (status > -1){
                        Toast.makeText(applicationContext,"delete successful",
                            Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(applicationContext,"something went wrong",
                            Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(applicationContext,"Fields cannot be empty",
                        Toast.LENGTH_LONG).show()
                }

            })
        dialogBuilder.setNegativeButton("Cancel"
            ,DialogInterface.OnClickListener { dialog, which ->
                  dialog.dismiss()
            })
        dialogBuilder.setNeutralButton("Help"
            ,DialogInterface.OnClickListener { dialog, which ->
               Toast.makeText(applicationContext,"Enter id to delete data"
                   ,Toast.LENGTH_LONG).show()
            })
        val b = dialogBuilder.create()
        b.show()
    }
}

















