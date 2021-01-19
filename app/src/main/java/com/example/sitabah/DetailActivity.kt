package com.example.sitabah

import android.content.Intent
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sitabah.model.ModelIbadah
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), OnMapReadyCallback {
    companion object {
        const val EXTRA_DETAIl = "extra_detail"
    }
     var DestinasiIbadah : ModelIbadah? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        DestinasiIbadah = intent.getParcelableExtra(EXTRA_DETAIl) as? ModelIbadah
        placeNameTextView.text = DestinasiIbadah?.ti_nama
        addressTextView.text = DestinasiIbadah?.ti_alamat

        Glide.with(this)
            .load("https://sitabah.000webhostapp.com/assets/foto/tempat_ibadah/" + DestinasiIbadah?.ti_foto)
            .apply(RequestOptions().override(500, 500))
            .into(imageViewPager)


        if (DestinasiIbadah?.ti_jenis == "1"){
            topologi.text = DestinasiIbadah?.ti_tipologi
            id_isi_tb_luas_tanah.text = DestinasiIbadah?.ti_luas_tanah
            id_isi_tb_status_tanah.text = DestinasiIbadah?.ti_status_tanah
            id_isi_tb_luas_bangunan.text = DestinasiIbadah?.ti_luas_bangunan
            id_isi_tb_tahun_berdiri.text = DestinasiIbadah?.ti_tahun_berdiri
            id_isi_tb_jamaah.text = DestinasiIbadah?.ti_jamaah
            id_isi_tb_imam.text = DestinasiIbadah?.ti_imam
            id_isi_tb_khatib.text = DestinasiIbadah?.ti_khatib
            id_isi_tb_remaja.text = DestinasiIbadah?.ti_remaja

            id_tb_ketua.visibility = View.GONE
            id_tb_keterangan.visibility = View.GONE
            id_tb_binaan_majelis.visibility = View.GONE
            id_tb_kondisi.visibility = View.GONE

        } else if (DestinasiIbadah?.ti_jenis == "2"){

            topologi.text = DestinasiIbadah?.jenis_nama
            id_isi_tb_kondisi.text = DestinasiIbadah?.ti_kondisi
            id_isi_tb_luas_tanah.text = DestinasiIbadah?.ti_luas_tanah
            id_isi_tb_luas_bangunan.text = DestinasiIbadah?.ti_luas_bangunan

//            imageView10.visibility = View.GONE

            id_tb_ketua.visibility = View.GONE
            id_tb_keterangan.visibility = View.GONE
            id_tb_binaan_majelis.visibility = View.GONE

            id_tb_status_tanah.visibility = View.GONE
            id_tb_tahun_berdiri.visibility = View.GONE
            id_tb_jamaah.visibility = View.GONE
            id_tb_imam.visibility = View.GONE
            id_tb_khatib.visibility = View.GONE
            id_tb_remaja.visibility = View.GONE

        }else if (DestinasiIbadah?.ti_jenis == "3") {
            id_isi_tb_ketua.text = DestinasiIbadah?.ti_ketua
            id_isi_tb_keterangan.text = DestinasiIbadah?.ti_keterangan

            topologi.text = DestinasiIbadah?.jenis_nama
//            imageView10.visibility = View.GONE

            id_tb_binaan_majelis.visibility = View.GONE
            id_tb_kondisi.visibility = View.GONE

            id_tb_luas_tanah.visibility = View.GONE
            id_tb_status_tanah.visibility = View.GONE
            id_tb_luas_bangunan.visibility = View.GONE
            id_tb_tahun_berdiri.visibility = View.GONE
            id_tb_jamaah.visibility = View.GONE
            id_tb_imam.visibility = View.GONE
            id_tb_khatib.visibility = View.GONE
            id_tb_remaja.visibility = View.GONE

        } else if (DestinasiIbadah?.ti_jenis == "4") {

            id_isi_tb_ketua.text = DestinasiIbadah?.ti_ketua
            id_isi_tb_binaan_majelis.text = DestinasiIbadah?.ti_binaan_majelis

            id_isi_tb_keterangan.visibility = View.GONE

            topologi.text = DestinasiIbadah?.jenis_nama
//            imageView10.visibility = View.GONE


            id_tb_keterangan.visibility = View.GONE
            id_tb_kondisi.visibility = View.GONE
            id_tb_luas_tanah.visibility = View.GONE
            id_tb_status_tanah.visibility = View.GONE
            id_tb_luas_bangunan.visibility = View.GONE
            id_tb_tahun_berdiri.visibility = View.GONE
            id_tb_jamaah.visibility = View.GONE
            id_tb_imam.visibility = View.GONE
            id_tb_khatib.visibility = View.GONE
            id_tb_remaja.visibility = View.GONE

        }else if (DestinasiIbadah?.ti_jenis == "5") {

            id_isi_tb_ketua.text = DestinasiIbadah?.ti_ketua
            id_isi_tb_binaan_majelis.text = DestinasiIbadah?.ti_binaan_majelis

            id_isi_tb_keterangan.visibility = View.GONE

            topologi.text = DestinasiIbadah?.jenis_nama
//            imageView10.visibility = View.GONE


            id_tb_keterangan.visibility = View.GONE
            id_tb_kondisi.visibility = View.GONE
            id_tb_luas_tanah.visibility = View.GONE
            id_tb_status_tanah.visibility = View.GONE
            id_tb_luas_bangunan.visibility = View.GONE
            id_tb_tahun_berdiri.visibility = View.GONE
            id_tb_jamaah.visibility = View.GONE
            id_tb_imam.visibility = View.GONE
            id_tb_khatib.visibility = View.GONE
            id_tb_remaja.visibility = View.GONE
        }

        addressTextView.text = "${DestinasiIbadah?.latitude}, ${DestinasiIbadah?.longitude}"
        phoneTextView.text = DestinasiIbadah?.ti_telepon
        id_isi_tb_alamat.text = DestinasiIbadah?.ti_alamat
        id_isi_tb_kabupaten.text = DestinasiIbadah?.kab_nama
        id_isi_tb_kecamatan.text = DestinasiIbadah?.kec_nama



        initUI(savedInstanceState)

        directionGoogleMaps.setOnClickListener {
            val uri =
                "http://maps.google.com/maps?q=loc:"+DestinasiIbadah!!.latitude +","+ DestinasiIbadah!!.longitude
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }


        textViewShare.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, DestinasiIbadah?.ti_id)
                var shareMessage = "\nTempat ibadah '${DestinasiIbadah?.ti_nama}' berada di alamat '${DestinasiIbadah?.ti_alamat}' dan Kecamatan '${DestinasiIbadah?.kec_nama}'" +
                        "\nSelengkapnya ada di Aplikasi SITABAH, Yuk Download\n\n"
                shareMessage =
                    """
                    ${shareMessage}https://drive.google.com/file/d/1L6bhfcvcxayoIYr3jFjaG-hhQ_913N1B/view?usp=sharing
                    """.trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "Pilih Metode"))
            } catch (e: Exception) {
                //e.toString();
            }
        }


        textViewcall.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:(0370) 623375")
            startActivity(intent)
        }

    }


    private fun initUI(savedInstanceState: Bundle?) {

        initToolbar()

        val toolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.collapsingToolbar)
        toolbarLayout.setExpandedTitleColor(
            ContextCompat.getColor(
                this,
                android.R.color.transparent
            )
        )

        placeMapView.onCreate(savedInstanceState)
        placeMapView.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {

        googleMap.setMinZoomPreference(12f)


        if (DestinasiIbadah?.latitude == null || DestinasiIbadah?.longitude == null ) {

        }else {

            val ny = LatLng(
                DestinasiIbadah?.latitude!!.toDouble(),
                DestinasiIbadah?.longitude!!.toDouble()

            )
            val markerOptions = MarkerOptions()
            markerOptions.position(ny)
            googleMap.addMarker(markerOptions)

            googleMap.moveCamera(CameraUpdateFactory.newLatLng(ny))
        }

    }

    public override fun onResume() {
        placeMapView.onResume()
        super.onResume()
    }

    public override fun onPause() {
        super.onPause()
        placeMapView.onPause()
    }

    public override fun onDestroy() {
        super.onDestroy()
        placeMapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        placeMapView.onLowMemory()
    }

    private fun initToolbar() {

        val toolbar: Toolbar? = findViewById<Toolbar>(R.id.toolbar)
        toolbar!!.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24)

        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.md_white_1000
                ), PorterDuff.Mode.SRC_ATOP
            )
        }

        toolbar.title = DestinasiIbadah?.ti_nama

        try {
            toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.md_white_1000))
        } catch (e: Exception) {
            Log.e("TEAMPS", "Can't set color.")
        }

        try {
            setSupportActionBar(toolbar)
        } catch (e: Exception) {
            Log.e("TEAMPS", "Error in set support action bar.")
        }

        try {
            if (supportActionBar != null) {
               toolbar.setNavigationOnClickListener {
                   onBackPressed()
               }
            }
        } catch (e: Exception) {
            Log.e("TEAMPS", "Error in set display home as up enabled.")
        }

    }

}