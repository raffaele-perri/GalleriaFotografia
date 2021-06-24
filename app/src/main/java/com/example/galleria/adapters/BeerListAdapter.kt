package com.example.galleria.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.galleria.R
import com.example.app_domain.model.Beer

class BeerListAdapter (private val beerList: List<Beer>, private val itemClickAction: (Beer) ->Unit = {}) :
    RecyclerView.Adapter<BeerListAdapter.BeerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.beer_holder, parent, false)
        return BeerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.textViewName.text = beerList[position].name
        holder.textViewTagLine.text = beerList[position].tagLine
        holder.itemView.setOnClickListener{itemClickAction(beerList[position])}
    }

    override fun getItemCount(): Int {
        return beerList.size
    }



    class BeerViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textViewName: TextView
        val textViewTagLine: TextView
        init {
            textViewName = view.findViewById(R.id.textViewName)
            textViewTagLine = view.findViewById(R.id.textViewTagLine)
        }



    }
}
