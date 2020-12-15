package com.example.sitabah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import com.example.sitabah.fragment.AboutFragment
import com.example.sitabah.fragment.MapFragment
import com.example.sitabah.fragment.MapsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            item -> when (item.itemId){
                R.id.navigation_map -> {
                    replaceTitle(getString(R.string.map))
                    replaceFragment(MapsFragment())
                    return@OnNavigationItemSelectedListener  true
                }
                R.id.navigation_list -> {
                    replaceTitle(getString(R.string.list))
                    replaceFragment(MapFragment())
                    return@OnNavigationItemSelectedListener  true
                }

                R.id.navigation_about -> {
                    replaceTitle(getString(R.string.about))
                    replaceFragment(AboutFragment())
                    return@OnNavigationItemSelectedListener  true
                }
            }
            false

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_map
    }

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