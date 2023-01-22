package com.wit.voguely.ui.main.cart

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wit.voguely.databinding.RecyclerViewSingleItemCartBinding
import com.wit.voguely.ui.main.home.Product

class CartAdapter :RecyclerView.Adapter<CartAdapter.ViewHolder> () {
    var onItemClick: ((Product)-> Unit)? = null
    var dataCart: List<Product> = listOf()

    inner class ViewHolder(val binding: RecyclerViewSingleItemCartBinding) :
            RecyclerView.ViewHolder(binding.root) {
                init {
                    binding.itemPic.setOnClickListener{
                        onItemClick?.invoke(dataCart[adapterPosition])
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerViewSingleItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.itemName.text = dataCart[position].itemName
        holder.binding.itemPrice.text = dataCart[position].price

        Glide
            .with(holder.itemView.context)
            .load(dataCart[position].urls)
            .into(holder.binding.itemPic)

    }

    override fun getItemCount() = dataCart.size
}