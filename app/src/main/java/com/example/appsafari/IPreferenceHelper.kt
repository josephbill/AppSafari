package com.example.appsafari

interface IPreferenceHelper {
    //defines get and set methods
    //in our example we are storing an API key and userID
    fun setApiKey(apikey: String)
    fun getApiKey() : String
    fun setUserId(userId: String)
    fun getUserId() : String
    fun clearPrefs()
}