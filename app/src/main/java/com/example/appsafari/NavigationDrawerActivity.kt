package com.example.appsafari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class NavigationDrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    //intializing tags for widgets
    private lateinit var drawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer)

        //view identity
        drawer = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        navView = findViewById(R.id.nav_drawer)
        //give nav view a listener
        navView.setNavigationItemSelectedListener(this)

        //hamburger icon
        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(this,drawer
           ,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        //attach drawer layout to listen to the toggle
        drawer.addDrawerListener(toggle)
        toggle.syncState()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_constraint -> {
                val intentCon = Intent(this@NavigationDrawerActivity,ConstraintLayoutActivity::class.java)
                startActivity(intentCon)
            }
            R.id.nav_relative -> {
                val intentRel = Intent(this@NavigationDrawerActivity,RelativeLayout::class.java)
                startActivity(intentRel)
            }
            R.id.nav_linear -> {
                Toast.makeText(applicationContext,"Linear clicked",Toast.LENGTH_LONG).show()
            }
        }
        return true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}