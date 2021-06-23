package com.example.appsafari

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,
                                     DATABASE_VERSION) {

    //define our constant variables
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "UserDatabase"
        private val TABLE_USERS = "UserTable"
        private val KEY_ID = "id"
        private  val KEY_NAME = "name"
        private val KEY_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
         //define our query
        val CREATE_CONTACTS_TABLE =
            ("CREATE TABLE " + TABLE_USERS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME +
                    " TEXT," + KEY_EMAIL + " TEXT" + ")")
        //execute the query
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
              db!!.execSQL("DROP TABLE IF EXISTS" + TABLE_USERS)
              onCreate(db)
    }

    //save data
    fun addUsers(sqliteModel: SqiliteModel): Long {
        //telling the db what to do
        val db = this.writableDatabase
        //define and place content
        val contentvalues = ContentValues()
        //put data to the respective fields
        contentvalues.put(KEY_ID,sqliteModel.userId)
        contentvalues.put(KEY_NAME,sqliteModel.userName)
        contentvalues.put(KEY_EMAIL,sqliteModel.userEmail)
        //query to insert to db
        val success = db.insert(TABLE_USERS,null,contentvalues)
        //close the db connection
        db.close()
        //return output of method
        return success
    }

    //function to view details
    fun readData() : List<SqiliteModel>{
        //get a resizable array
        //instance of the ArrayList class
        val userArray: ArrayList<SqiliteModel> = ArrayList<SqiliteModel>()
        //define our fetch query
        val selectQuery = "SELECT * FROM $TABLE_USERS"
        //define what the sqlite should do
        val db = this.readableDatabase
        //reading our data
        var cursor: Cursor? = null
        //declare a try and catch and the use will be incase the data is not there or
        //the database undergoes and upgrade we need to prevent the crush
        try {
            cursor = db.rawQuery(selectQuery,null)
        } catch (e: SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        //iterate over records in db and store them in model class
        var userId: Int
        var userName: String
        var userEmail: String
        //using cursor to pick records
        if (cursor.moveToFirst()){
            //create a loop for the fetching process
            do {
                userId = cursor.getInt(cursor.getColumnIndex("id"))
                userName = cursor.getString(cursor.getColumnIndex("name"))
                userEmail = cursor.getString(cursor.getColumnIndex("email"))
                //taking the dta to the model class
                val users = SqiliteModel(userId = userId, userName = userName,
                             userEmail = userEmail)
                userArray.add(users)
            } while (cursor.moveToNext())
        }
        return userArray
    }



}
































