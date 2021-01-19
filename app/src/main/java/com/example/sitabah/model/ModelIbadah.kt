package com.example.sitabah.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelIbadah(
    val ti_id: String? = null,
    val ti_jenis: String? = null,
    val ti_tipologi: String? = null,
    val ti_nama: String? = null,

    val ti_alamat: String? = null,
    val kab_nama: String? = null,
    val kec_nama: String? = null,
    val ti_luas_tanah: String? = null,

    val ti_status_tanah: String? = null,
    val ti_luas_bangunan: String? = null,
    val ti_tahun_berdiri: String? = null,
    val ti_jamaah: String? = null,

    val ti_imam: String? = null,
    val ti_khatib: String? = null,
    val ti_remaja: String? = null,
    val ti_ketua: String? = null,

    val ti_keterangan: String? = null,
    val ti_binaan_majelis: String? = null,
    val ti_kondisi: String? = null,
    val ti_telepon: String? = null,

    val latitude: String? = null,
    val longitude: String? = null,
    val ti_foto: String? = null,
    val mapicon: String? = null,

    val jenis_nama: String? = null

) : Parcelable {
    override fun toString(): String = this.ti_nama!!
}
//089662342125