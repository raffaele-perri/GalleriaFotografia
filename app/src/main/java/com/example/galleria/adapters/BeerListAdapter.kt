package com.example.galleria.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_domain.model.Beer
import com.example.galleria.databinding.BeerHolderBinding

class BeerListAdapter :
    RecyclerView.Adapter<BeerListAdapter.BeerViewHolder>() {
    private lateinit var context: Context
    var listener: ((beer : Beer) -> Unit)? = null
    var beerList: List<Beer> = listOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        //val view  = LayoutInflater.from(parent.context).inflate(R.layout.beer_holder, parent, false)
        //return BeerViewHolder(view)
        val binding = BeerHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        context = parent.context
        return BeerViewHolder(binding)

    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
//        holder.binding.textViewName.text = beerList[position].name
//        holder.binding.textViewTagLine.text = beerList[position].tagLine
//        Glide.with(context).load(beerList[position].imageUrl).into(holder.binding.imageViewBeer)
//        holder.itemView.setOnClickListener{ listener?.let { it1 -> it1(beerList[position]) } }
        holder.bind(beerList[position])
    }

    override fun getItemCount(): Int {
        return beerList.size
    }



    inner class BeerViewHolder(private val binding: BeerHolderBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(beer : Beer){
            binding.apply {
                textViewName.text = beer.name
                textViewTagLine.text = beer.tagLine
                Glide.with(context).load(beer.imageUrl).into(imageViewBeer)
                root.setOnClickListener{
                    listener?.invoke(beer)
                }
            }
        }
    }

}
