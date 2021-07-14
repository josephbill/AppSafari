package com.example.appsafari

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_maps2.*
import java.io.IOException
import java.lang.ClassCastException
import java.util.*

class MapsActivity2 : AppCompatActivity(), OnMapReadyCallback , PermissionListener{
    companion object {
        const val REQUEST_CHECK_SETTINGS = 43
    }
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps2)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        //intializing fusedLocationProviderClient
        fusedLocationProviderClient = FusedLocationProviderClient(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
           mMap = googleMap?: return
          if (isPermissionGiven()){
              if (ActivityCompat.checkSelfPermission(
                      this,
                      Manifest.permission.ACCESS_FINE_LOCATION
                  ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                      this,
                      Manifest.permission.ACCESS_COARSE_LOCATION
                  ) != PackageManager.PERMISSION_GRANTED
              ) {
                  // TODO: Consider calling
                  //    ActivityCompat#requestPermissions
                  // here to request the missing permissions, and then overriding
                  //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                  //                                          int[] grantResults)
                  // to handle the case where the user grants the permission. See the documentation
                  // for ActivityCompat#requestPermissions for more details.
                  return
              }
              mMap.isMyLocationEnabled = true
              mMap.uiSettings.isMyLocationButtonEnabled = true
              mMap.uiSettings.isZoomControlsEnabled = true
              getCurrentLocation()
          } else {
              givePermission()
          }
        // Add a marker in Sydney and move the camera
        //val sydney = LatLng(-34.0, 151.0)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun isPermissionGiven():Boolean {
        return ActivityCompat.checkSelfPermission(this,
                   android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED
    }

    private fun givePermission(){
        Dexter.withActivity(this)
            .withPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(this)
            .check()
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        getCurrentLocation()
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(this,
            "Turn on Location.Permission required for showing location",
                       Toast.LENGTH_LONG).show();
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {
        token!!.continuePermissionRequest()
    }

    private fun getCurrentLocation(){
        val locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = (10 * 1000).toLong() //approximately 10 seconds
        locationRequest.fastestInterval = 2000 //approxiametly 2 seconds

        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(locationRequest)
        val locationSettingsRequest  = builder.build()
        //turn on location
        val result = LocationServices.getSettingsClient(this).checkLocationSettings(
            locationSettingsRequest
        )
        result.addOnCompleteListener{
              try{
                  val response  = it.getResult(ApiException::class.java)
                  if (response!!.locationSettingsStates.isLocationPresent){
                      getLastLocation()
                  }
              } catch (exception: ApiException){
                   when(exception.statusCode) {
                       LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                           val resolvable  = exception as ResolvableApiException
                           resolvable.startResolutionForResult(this, REQUEST_CHECK_SETTINGS)
                       } catch (e: IntentSender.SendIntentException){
                           e.printStackTrace()
                       } catch (e: ClassCastException){
                           e.printStackTrace()
                       }
                       LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {}
                   }
              }
        }
    }

    private fun getLastLocation(){
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationProviderClient.lastLocation
            .addOnCompleteListener(this){
                if (it.isSuccessful && it.result != null){
                    val location = it.result
                    var address = "No known address"
                    var geocoder = Geocoder(this, Locale.getDefault())
                    val addresses: List<Address>
                    try {
                        addresses = geocoder.getFromLocation(location!!.latitude,
                            location!!.longitude, 1)
                        if (addresses.isNotEmpty()){
                            address = addresses[0].getAddressLine(0)
                        }
                    } catch (e: IOException){
                        e.printStackTrace()
                    }
                    //marker
                    val icon = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(this.resources, R.drawable.ic_pickup))
                    mMap.addMarker(
                        MarkerOptions()
                            .position(LatLng(location!!.latitude,
                                             location!!.longitude))
                            .title("current location")
                            .snippet(address)
                            .icon(icon)
                    )

                    //camera position
                    val cameraPosition = CameraPosition.Builder()
                        .target(LatLng(location!!.latitude,location!!.longitude))
                        .zoom(17f)
                        .build()
                    mMap.moveCamera(CameraUpdateFactory.
                    newCameraPosition(cameraPosition))
                } else {
                    Toast.makeText(this,"No current location found",
                    Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode){
            REQUEST_CHECK_SETTINGS -> {
                if (resultCode == Activity.RESULT_OK){
                    getCurrentLocation()
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }




}



