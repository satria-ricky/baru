package com.example.sitabah.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sitabah.DetailActivity
import com.example.sitabah.MainActivity
import com.example.sitabah.R
import com.example.sitabah.model.ModelIbadah
import com.example.sitabah.viewmodel.IbadahViewModel
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.fragment_maps.*
import java.io.IOException
import java.util.*



class MapsFragment : Fragment(), OnMapReadyCallback, PermissionListener {
    lateinit var mainViewModel : IbadahViewModel
    private lateinit var destination : ArrayList<ModelIbadah>
    var markerOptions = MarkerOptions()
    var ceck = 0
    var mMap : GoogleMap? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
        fusedLocationProviderClient = FusedLocationProviderClient(requireActivity())
    }

    @SuppressLint("ResourceType")
    fun loadMarker(){
        clean.setOnClickListener {
            search.setText("")
        }

        val kategori = resources.getStringArray(R.array.kategori)

        if (listItem != null) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item, kategori
            )
            listItem.adapter = adapter

            listItem.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    val id = listItem.selectedItemPosition

                    if(id == 0){
                        cari("","")
                    }else if(id ==1){
                        cari("","1")
                    }
                    else if(id ==2){
                        cari("","2")
                    }
                    else if(id ==3){
                        cari("","3")
                    }
                    else if(id ==4){
                        cari("","4")
                    }
                    else if(id ==5){
                        cari("","5")
                    }
//                    Toast.makeText(
//                        requireContext(),
//                        "${adapter.getItem(id)}", Toast.LENGTH_SHORT
//                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

    }


    override fun onResume() {
        super.onResume()
            activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            loadMarker()
            cari("","")

    }

fun cari(latitude: String?, jenis_id: String?){

    val klentengBitmap = BitmapFactory.decodeResource(resources, R.drawable.klenteng)
    val klentengMarker = Bitmap.createScaledBitmap(klentengBitmap, 90, 90, false)
    val masjidBitmap = BitmapFactory.decodeResource(resources, R.drawable.masjid)
    val masjidMarker = Bitmap.createScaledBitmap(masjidBitmap, 90, 90, false)
    val puraBitmap = BitmapFactory.decodeResource(resources, R.drawable.pura)
    val puraMarker = Bitmap.createScaledBitmap(puraBitmap, 90, 90, false)
    val viharaBitmap = BitmapFactory.decodeResource(resources, R.drawable.vihara)
    val viharaMarker = Bitmap.createScaledBitmap(viharaBitmap, 90, 90, false)
    val gerejaBitmap = BitmapFactory.decodeResource(resources, R.drawable.gereja)
    val gerejaMarker = Bitmap.createScaledBitmap(gerejaBitmap, 90, 90, false)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            IbadahViewModel::class.java
        )
        mainViewModel.setLaLong(latitude!!, jenis_id!!)

        mainViewModel.getDestination().observe(this, Observer { destination ->
            mMap!!.clear()
            if (destination != null) {

                destination.forEach {

                    if (it.latitude == null || it.longitude == null) {

                    } else {

                        val lat = it.latitude!!.toDouble()
                        val lng = it.longitude!!.toDouble()

                        val latlng = LatLng(lat, lng)

                        val dataMarker = markerOptions.position(latlng)
                            .title(it.ti_nama)
                            .snippet(it.ti_alamat)

                        if (it.ti_jenis == "1") {
                            dataMarker.icon(BitmapDescriptorFactory.fromBitmap(masjidMarker))
                        } else if (it.ti_jenis == "2") {
                            dataMarker.icon(BitmapDescriptorFactory.fromBitmap(puraMarker))
                        } else if (it.ti_jenis == "3") {
                            dataMarker.icon(BitmapDescriptorFactory.fromBitmap(gerejaMarker))
                        } else if (it.ti_jenis == "4") {
                            dataMarker.icon(BitmapDescriptorFactory.fromBitmap(viharaMarker))
                        } else if (it.ti_jenis == "5") {
                            dataMarker.icon(BitmapDescriptorFactory.fromBitmap(klentengMarker))
                        }


                        val modelIbadah = it
                        val m = mMap!!.addMarker(markerOptions)
                        m.tag = modelIbadah

                        Log.d("Tes", "${m.tag}")
                        mMap!!.setOnInfoWindowClickListener { p0 ->
                            val modelIbadah = p0.tag as ModelIbadah


                            val intent = Intent(context, DetailActivity::class.java)
                            intent.putExtra(DetailActivity.EXTRA_DETAIl, modelIbadah)
                            startActivity(intent)

                        }

                        if (search.text.equals("")) {
                            clean.visibility = View.GONE
                            search.isCursorVisible = false
                        } else {
                            search.isCursorVisible = true
                            clean.visibility = View.VISIBLE

                            val var_adapter = ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                destination
                            )

                            search.threshold = 0
                            search.setAdapter(var_adapter)

                            search.setOnItemClickListener { parent, view, position, id ->

                                var position = search.adapter.getItem(position) as ModelIbadah

                                if (position.latitude == null || position == null) {
                                    Toast.makeText(
                                        requireContext(),
                                        "Maaf, garis bujur dan garis lintang dari loksai ini belum ditemukan",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
                                    var latitude = position.latitude!!.toDouble()
                                    var longitude = position.longitude!!.toDouble()
                                    val kamera_posisi = CameraPosition.builder()
                                        .target(LatLng(latitude, longitude))
                                        .zoom(15f)
                                        .build()
                                    mMap!!.animateCamera(
                                        CameraUpdateFactory.newCameraPosition(
                                            kamera_posisi
                                        )
                                    )
                                }
                            }
                        }

                    }

                }

            } else {
                mMap!!.clear()
            }
        })
}

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!

//            val cameraPosition = CameraPosition.builder()
//                .target(LatLng(-8.577669, 116.100659))
//                .zoom(14f)
//                .build()
//            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
//
//
//        googleMap.setPadding(0,300,0,0)
//        googleMap.isMyLocationEnabled = true
//        googleMap.isTrafficEnabled = true
//        googleMap.uiSettings.isMyLocationButtonEnabled = true
//        googleMap.uiSettings.isZoomControlsEnabled = true

        if (isPermissionGiven()){
            val cameraPosition = CameraPosition.Builder()
                .target(LatLng(-8.577669, 116.100659))
                .zoom(14f)
                .build()

            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

            imgMyLocation.setOnClickListener {
                getCurrentLocation()
            }

            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
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
            googleMap.isMyLocationEnabled = true
            googleMap.isTrafficEnabled = true
            googleMap.uiSettings.isZoomControlsEnabled = true
            getCurrentLocation()
        } else {
            givePermission()
        }

    }

    private fun isPermissionGiven(): Boolean{
        return ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun givePermission() {
        Dexter.withActivity(activity)
            .withPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(this)
            .check()
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        getCurrentLocation()
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {
        token!!.continuePermissionRequest()
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(requireContext(), "Permission required for showing location", Toast.LENGTH_LONG).show()
    }

    private fun getCurrentLocation() {

        val locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = (10 * 1000).toLong()
        locationRequest.fastestInterval = 2000

        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(locationRequest)
        val locationSettingsRequest = builder.build()

        val result = LocationServices.getSettingsClient(requireActivity()).checkLocationSettings(locationSettingsRequest)
        result.addOnCompleteListener { task ->
            try {
                val response = task.getResult(ApiException::class.java)
                if (response!!.locationSettingsStates.isLocationPresent){
                    getLastLocation()
                }
            } catch (exception: ApiException) {
                when (exception.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                        val resolvable = exception as ResolvableApiException
                        resolvable.startResolutionForResult(activity,
                            MainActivity.REQUEST_CHECK_SETTINGS
                        )
                    } catch (e: IntentSender.SendIntentException) {
                    } catch (e: ClassCastException) {
                    }

                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> { }
                }
            }
        }
    }

    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
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
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful && task.result != null) {
                    val mLastLocation = task.result

                    var address = "Alamat tidak ditemukan"

                    val gcd = Geocoder(context, Locale.getDefault())
                    val addresses: List<Address>
                    try {
                        addresses = gcd.getFromLocation(mLastLocation!!.latitude, mLastLocation.longitude, 1)
                        if (addresses.isNotEmpty()) {
                            address = addresses[0].getAddressLine(0)
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }


                    val cameraPosition = CameraPosition.Builder()
                        .target(LatLng(mLastLocation!!.latitude, mLastLocation!!.longitude))
                        .zoom(14f)
                        .build()
                    mMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                } else {
                    Toast.makeText(requireContext(), "Lokasi terkini tidak ditemukan", Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when (requestCode) {
            MainActivity.REQUEST_CHECK_SETTINGS -> {
                if (resultCode == Activity.RESULT_OK) {
                    getCurrentLocation()
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)

    }
}