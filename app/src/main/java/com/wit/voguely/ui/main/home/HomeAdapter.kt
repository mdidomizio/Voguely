package com.wit.voguely.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wit.voguely.databinding.RecyclerViewSingleItemLayoutBinding


class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder> () {
    private val mProducts: List<Product> = emptyList()
    var onItemClick: ((Product) -> Unit)? = null
    var onSeeMoreClicked: ((Product, View) -> Unit)? = null


    var data: MutableList<Product> = mutableListOf()

    inner class ViewHolder(val binding: RecyclerViewSingleItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
       init {
            binding.itemPic.setOnClickListener {
                onItemClick?.invoke(data[adapterPosition])
            }
           binding.seeMoreIcon.setOnClickListener{
               onSeeMoreClicked?.invoke(data[adapterPosition], it)
           }


        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = RecyclerViewSingleItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.binding.itemName.text = data[position].name
            holder.binding.itemPrice.text = "${data[position].currency} ${data[position].price.toString()}"
            holder.binding.itemRate.text = data[position].rating.toString()
            holder.binding.itemReviews.text = "${data[position].reviews.toString()} reviews"

            Glide
                .with(holder.itemView.context)
                .load(data[position].image)
                .into(holder.binding.itemPic)


        }


        override fun getItemCount() = data.size

    fun updateProductListItems(products: List<Product>) {
        val diffCallback = ProductDiffCallback(this.data, products)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        data.clear()
        data.addAll(products)
        diffResult.dispatchUpdatesTo(this)
    }

    }


