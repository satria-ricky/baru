package com.example.sitabah.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sitabah.api.ServiceBuilder
import com.example.sitabah.model.IbadahRespons
import com.example.sitabah.model.ModelIbadah
import org.jetbrains.annotations.Nullable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IbadahViewModel : AndroidViewModel(Application()) {

    private val destination =  MutableLiveData<ArrayList<ModelIbadah>>()
    private val apiService = ServiceBuilder.create()

    fun setDestination(){
        val dataDestination = ArrayList<ModelIbadah>()
        apiService.getDestinationList().enqueue(object :
            Callback<IbadahRespons> {
            override fun onFailure(call: Call<IbadahRespons>, t: Throwable) {
                Log.d("FailureLog", t.toString())
            }

            override fun onResponse(call: Call<IbadahRespons>, response: Response<IbadahRespons>) {

                if(response.isSuccessful){
                    val dataDes = response.body()
                    Log.d("ResponseLog", response.toString())
                    dataDestination.addAll(dataDes!!.data)
                    destination.postValue(dataDestination)
                }else{
                    Log.d("gagalresponse", response.toString())
//                    Toast.makeText(getApplication(), "Gagal", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun setJenis(jenis : String){
        val dataDestination = ArrayList<ModelIbadah>()
        apiService.getJenis(jenis).enqueue(object :
            Callback<IbadahRespons> {
            override fun onFailure(call: Call<IbadahRespons>, t: Throwable) {
                Log.d("FailureLog", t.toString())
            }

            override fun onResponse(call: Call<IbadahRespons>, response: Response<IbadahRespons>) {

                if(response.isSuccessful){
                    val dataDes = response.body()
                    Log.d("ResponseLog", response.toString())
                    dataDestination.addAll(dataDes!!.data)
                    destination.postValue(dataDestination)
                }else{
                    Log.d("gagalresponse", response.toString())
                }
            }
        })
    }


    fun setLaLong (latitude : String, jenis_id: String){
        val dataDestination = ArrayList<ModelIbadah>()

        apiService.getbyLat(latitude, jenis_id).enqueue(object :

            Callback<IbadahRespons> {
            override fun onFailure(call: Call<IbadahRespons>, t: Throwable) {
                Log.d("FailureLog", t.toString())
            }

            override fun onResponse(call: Call<IbadahRespons>, response: Response<IbadahRespons>) {

                if(response.isSuccessful){
                    val dataDes = response.body()
                    Log.d("ResponseLog", response.toString())
                    dataDestination.addAll(dataDes!!.data)
                    destination.postValue(dataDestination)
                }else{
                    Log.d("gagalresponse", response.toString())
                }
            }
        })
    }


    fun getDestination(): LiveData<ArrayList<ModelIbadah>> {
        return destination
    }

}