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

            R.id.nav_relative -> {
                val intentBottom = Intent(this@NavigationDrawerActivity,BottomNavActivity::class.java)
                startActivity(intentBottom)
            }
            R.id.nav_recycle -> {
                val intentRecyle = Intent(this@NavigationDrawerActivity,RecyelerviewActivity::class.java)
                startActivity(intentRecyle)
            }
            R.id.nav_intentsharing -> {
                val intentRecyle = Intent(this@NavigationDrawerActivity,IntentSharingActivity::class.java)
                startActivity(intentRecyle)
            }
            R.id.nav_frags -> {
                val intentRecyle = Intent(this@NavigationDrawerActivity,FragmentClass::class.java)
                startActivity(intentRecyle)
            }
            R.id.nav_back -> {
                val intback = Intent(this@NavigationDrawerActivity,BackgroundLogic::class.java)
                startActivity(intback)
            }
            R.id.nav_frag_interface -> {
                val intback = Intent(this@NavigationDrawerActivity,FragmentClass::class.java)
                startActivity(intback)
            }
            R.id.nav_frag_model -> {
                val intback = Intent(this@NavigationDrawerActivity,FragmentViewModel::class.java)
                startActivity(intback)
            }
            R.id.nav_sql -> {
                val intback = Intent(this@NavigationDrawerActivity,SqliteActivity::class.java)
                startActivity(intback)
            }
            R.id.nav_sharedPrefs -> {
                val intsharedPrefs = Intent(this@NavigationDrawerActivity,SharedPreferenceActivity::class.java)
                startActivity(intsharedPrefs)
            }
            R.id.nav_networking -> {
                val intsharedPrefs = Intent(this@NavigationDrawerActivity,AndroidNetworking::class.java)
                startActivity(intsharedPrefs)
            }
            R.id.nav_postUpdateDelete -> {
                val intentPost = Intent(this@NavigationDrawerActivity,PostUpdateDelete::class.java)
                startActivity(intentPost)
            }
            R.id.nav_maps -> {
                val intentMaps = Intent(this@NavigationDrawerActivity,MapsActivity2::class.java)
                startActivity(intentMaps)
            }
            R.id.nav_user_location -> {
                val intentUserLoc = Intent(this@NavigationDrawerActivity,MainActivity2::class.java)
                startActivity(intentUserLoc)
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