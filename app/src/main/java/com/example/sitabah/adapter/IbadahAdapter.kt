package com.example.sitabah.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sitabah.DetailActivity
import com.example.sitabah.R
import com.example.sitabah.model.ModelIbadah
import kotlinx.android.synthetic.main.list_ibadah.view.*

class IbadahAdapter(private val destinationList: ArrayList<ModelIbadah>): RecyclerView.Adapter<IbadahAdapter.ViewHolder>(),
    Filterable {
    private var filterListResult: ArrayList<ModelIbadah> = destinationList

    fun setData(items: ArrayList<ModelIbadah>) {

        filterListResult.clear()
        filterListResult.addAll(items)

        notifyDataSetChanged()
    }

    fun setFlter(items: ModelIbadah){
        filterListResult.clear()
        filterListResult.addAll(listOf(items))
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_ibadah, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return filterListResult.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filterListResult[position])
    }

    //Class Holder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(ibadah: ModelIbadah) {
            with(itemView){
                tvNamaHotel.text = ibadah.ti_nama
                if(ibadah.ti_foto == ""){
                    imgHotel.setImageResource(R.drawable.about_icon)
                }else{
                    Glide.with(context)
                        .load("https://sitabah.000webhostapp.com/assets/foto/tempat_ibadah/"+ibadah.ti_foto)
                        .apply(RequestOptions().override(500, 500))
                        .into(imgHotel)
                }

//
                itemView.setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DETAIl, ibadah)
                    context.startActivity(intent)
                }

            }
        }
    }

    override fun getFilter(): Filter {
        return object: Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if(charSearch.isEmpty())
                    filterListResult = destinationList
                else {
                    val resultlist = ArrayList<ModelIbadah>()
                    for (row in destinationList) {
                        if (row.ti_nama!!.toLowerCase().contains(charSearch.toLowerCase()) || row.ti_jenis!!.toLowerCase().contains(charSearch.toLowerCase()))
                            resultlist.add(row)
                    }
                    filterListResult = resultlist
                }
                val filterResult = FilterResults()
                filterResult.values = filterListResult
                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterListResult = results!!.values as ArrayList<ModelIbadah>
                notifyDataSetChanged()
            }

        }
    }


}