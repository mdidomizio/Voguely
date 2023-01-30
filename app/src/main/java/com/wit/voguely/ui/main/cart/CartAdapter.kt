package com.wit.voguely.ui.main.cart


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wit.voguely.databinding.RecyclerViewSingleItemCartBinding
import com.wit.voguely.ui.main.home.Product



class CartAdapter :RecyclerView.Adapter<CartAdapter.ViewHolder> () {
    var onItemClick: ((Product)-> Unit)? = null

    var dataCart: List<CartItem> = listOf()

    inner class ViewHolder(val binding: RecyclerViewSingleItemCartBinding) :
            RecyclerView.ViewHolder(binding.root) {
                init {
                    //addItemToCart()

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
        holder.binding.itemNameCart.text = dataCart[position].product.name
        holder.binding.itemPriceCart.text = "${dataCart[position].product.currency} ${dataCart[position].product.price.toString()}"
        holder.binding.itemQuantityCart.text = "x ${dataCart[position].quantity.toString()}"

        Glide
            .with(holder.itemView.context)
            .load(dataCart[position].product.image)
            .into(holder.binding.itemPicCart)

    }

    override fun getItemCount() = dataCart.size
}