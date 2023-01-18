package com.wit.voguely.ui.main.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wit.voguely.databinding.FragmentHomeBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder> () {
    var data : MutableList<DataItemClass> = mutableListOf()

    inner class ViewHolder(val binding:FragmentHomeBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            //TODO => binding.singleItem.setOnClickListener{
            //                onItemClick?.invoke(data[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

