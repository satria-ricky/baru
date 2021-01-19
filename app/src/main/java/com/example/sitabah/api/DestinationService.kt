package com.example.sitabah.api

import com.example.sitabah.model.IbadahRespons

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DestinationService {

    @GET("c_tempat_ibadah")
    fun getDestinationList(): Call<IbadahRespons>

    @GET("c_tempat_ibadah")
    fun getJenis(@Query("jenis_id") id_kategori: String): Call<IbadahRespons>

    @GET("c_lat")
    fun getbyLat(@Query("latitude") latitude : String? = null, @Query("jenis_id") jenis_id: String? = null): Call<IbadahRespons>
}