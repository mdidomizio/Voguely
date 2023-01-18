package com.wit.voguely.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wit.voguely.databinding.RecyclerViewSingleItemLayoutBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder> () {
    var data : List<Product> = listOf()

    inner class ViewHolder(val binding:RecyclerViewSingleItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            //TODO (maybe) => binding.singleItem.setOnClickListener{
            //                onItemClick?.invoke(data[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerViewSingleItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.itemName.text = data[position].itemName
        holder.binding.itemPrice.text = data[position].price
        holder.binding.itemRate.text = data[position].rate
        holder.binding.itemReviews.text = data[position].review

        Glide
            .with(holder.itemView.context)
            .load(data[position].urls)
            .into(holder.binding.itemPic)
    }

    override fun getItemCount() = data.size
}

