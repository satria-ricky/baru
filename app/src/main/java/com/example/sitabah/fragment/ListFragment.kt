package com.example.sitabah.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sitabah.R
import com.example.sitabah.adapter.IbadahAdapter
import com.example.sitabah.model.ModelIbadah
import com.example.sitabah.viewmodel.IbadahViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_maps.*
import java.util.*
import kotlin.collections.ArrayList


class ListFragment : Fragment(){
    private val destination = ArrayList<ModelIbadah>()
var ceck = 0
    lateinit var destinationAdapter : IbadahAdapter
    lateinit var mainViewModel : IbadahViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
            loadIbdahList()
        swiperef.setOnRefreshListener {
            Handler().postDelayed({ // Berhenti berputar/refreshing
                loadIbdahList()                    // refresh your list contents somehow
                swiperef.isRefreshing = false
            }, 2000)
   // reset the SwipeRefreshLayout (stop the loading spinner)
        }
    }


    @SuppressLint("ResourceType")
    private fun loadIbdahList() {
        destinationAdapter = IbadahAdapter(destination)
        destinationAdapter.notifyDataSetChanged()

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            IbadahViewModel::class.java
        )


        mainViewModel.setDestination()
        rvWisata.setHasFixedSize(true)
        rvWisata.layoutManager = LinearLayoutManager(context)
        rvWisata.adapter = destinationAdapter

        mainViewModel.getDestination().observe(this, Observer { destination ->
            if (destination != null) {
                destinationAdapter.setData(destination)

                searchView.onActionViewExpanded()
                searchView.clearFocus()
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {

                        destinationAdapter.filter.filter(query)
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {

                        destinationAdapter.filter.filter(newText)

                        return false
                    }

                })

                val kategori = resources.getStringArray(R.array.kategori)

                if (listItems != null) {
                    val adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_dropdown_item, kategori
                    )
                    listItems.adapter = adapter

                    listItems.onItemSelectedListener = object :
                        AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: View, position: Int, id: Long
                        ) {
                            val id = listItems.selectedItemPosition

                            if (id == 0) {
                                destinationAdapter.filter.filter("")
                            } else if (id == 1) {
                                destinationAdapter.filter.filter("1")
                            } else if (id == 2) {
                                destinationAdapter.filter.filter("2")
                            } else if (id == 3) {
                                destinationAdapter.filter.filter("3")
                            } else if (id == 4) {
                                destinationAdapter.filter.filter("4")
                            } else if (id == 5) {
                                destinationAdapter.filter.filter("5")
                            }
//                            Toast.makeText(
//                                requireContext(),
//                                "${adapter.getItem(id)}", Toast.LENGTH_SHORT
//                            ).show()
                        }

                        override fun onNothingSelected(parent: AdapterView<*>) {
                            // write code to perform some action
                        }
                    }
                }


//                val someArray = resources.getStringArray(R.array.kategori)
//
//                someArray.forEach {
//                    val chip = Chip(context)
//                    chip.isCheckable = true
//                    chip.chipBackgroundColor = resources.getColorStateList(R.drawable.bg_chip)
//                    chip.text = it
//                    chipGroup.addView(chip)
//
//                }
//                var lastCheckedId = View.NO_ID
//                chipGroup.setOnCheckedChangeListener { chipGroup, i ->
//                    if(i == View.NO_ID){
//                        chipGroup.check(lastCheckedId)
//                        Toast.makeText(context, "$lastCheckedId", Toast.LENGTH_LONG).show()
//                        return@setOnCheckedChangeListener
//                    }
//
//                    var j = i%7
//                    if (ceck > 0){
//                        j--
//                    }
//
//
//                    Toast.makeText(context, "$j", Toast.LENGTH_LONG).show()
//                    if(j == 0){
//                        destinationAdapter.filter.filter("")
//                    }else if(j == 1) {
//                        destinationAdapter.filter.filter("1")
//                    }else if(j == 2) {
//                        destinationAdapter.filter.filter("2")
//                    }else if(j == 3) {
//                        destinationAdapter.filter.filter("3")
//                    }else if(j == 4) {
//                        destinationAdapter.filter.filter("4")
//                    }else if(j == 5) {
//                        destinationAdapter.filter.filter("5")
//                    }else{
//                        destinationAdapter.filter.filter("zzzzzzzz")
//                    }
//                }


            } else {
                Toast.makeText(context, "Data tidak ada", Toast.LENGTH_LONG).show()
            }
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

}