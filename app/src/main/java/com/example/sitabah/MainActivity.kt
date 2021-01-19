package com.example.sitabah

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.sitabah.fragment.ListFragment
import com.example.sitabah.fragment.AboutFragment
import com.example.sitabah.fragment.MapsFragment
import com.fxn.ariana.Ariana
import com.fxn.ariana.ArianaBackgroundListener
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.http.Tag
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CHECK_SETTINGS = 43
    }
//
//    private val mOnNavigationItemSelectedListener =
//        BottomNavigationView.OnNavigationItemSelectedListener {
//            item -> when (item.itemId){
//                R.id.navigation_map -> {
//                    replaceTitle(getString(R.string.map))
//                    replaceFragment(MapsFragment())
//                    return@OnNavigationItemSelectedListener  true
//                }
//                R.id.navigation_list -> {
//                    replaceTitle(getString(R.string.list))
//                    replaceFragment(com.example.sitabah.fragment.ListFragment())
//                    return@OnNavigationItemSelectedListener  true
//                }
//
//                R.id.navigation_about -> {
//                    replaceTitle(getString(R.string.about))
//                    replaceFragment(AboutFragment())
//                    return@OnNavigationItemSelectedListener  true
//                }
//            }
//            false
//
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        menu_bottom.setOnItemSelectedListener { id ->
            var fragment: Fragment? = null
            when (id) {
                R.id.navigation_list -> {
                    replaceTitle(getString(R.string.list))
                    replaceFragment(ListFragment())
                    return@setOnItemSelectedListener
                }
                R.id.navigation_map -> {

                    replaceTitle(getString(R.string.map))
                    replaceFragment(MapsFragment())
                    return@setOnItemSelectedListener
                }
                R.id.navigation_about -> {
                    replaceTitle(getString(R.string.about))
                    replaceFragment(AboutFragment())
                    return@setOnItemSelectedListener
                }
            }
        }


        menu_bottom.setItemSelected(R.id.navigation_map)
        supportActionBar!!.hide()
    }

//
    private fun replaceFragment (fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.layout_container, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    private  fun replaceTitle(title: String){
        supportActionBar?.title = title
    }

}