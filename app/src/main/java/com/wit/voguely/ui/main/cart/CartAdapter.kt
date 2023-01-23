package com.wit.voguely.ui.main.cart


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wit.voguely.databinding.RecyclerViewSingleItemCartBinding
import com.wit.voguely.ui.main.home.Product
import io.paperdb.Paper

class CartAdapter :RecyclerView.Adapter<CartAdapter.ViewHolder> () {
    var onItemClick: ((Product)-> Unit)? = null

    var dataCart: List<Product> = listOf()

    inner class ViewHolder(val binding: RecyclerViewSingleItemCartBinding) :
            RecyclerView.ViewHolder(binding.root) {
                init {
                    /*
                    binding.itemPicCart.setOnClickListener{
                        onItemClick?.invoke(dataCart[adapterPosition])
                    }*/

                    fun bindProduct(product: Product){
                        binding.itemPicCart.setOnClickListener{ view ->
                            val item = CartItem(product)
                            ShoppingCart.addItem(item)
                        }
                        binding.cancelIcon.setOnClickListener {view->
                            val item = CartItem(product)
                            ShoppingCart.removeItem(item, itemView.context)
                        }

                        binding.itemQuantityCart.text = ShoppingCart.getShoppingCartSize().toString()
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
        holder.binding.itemNameCart.text = dataCart[position].itemName
        holder.binding.itemPriceCart.text = dataCart[position].price
       //TODO create a variable for the amount of items selected in the cart=>
        holder.binding.itemQuantityCart.text = "x1"

        Glide
            .with(holder.itemView.context)
            .load(dataCart[position].urls)
            .into(holder.binding.itemPicCart)

    }

    override fun getItemCount() = dataCart.size
}