package com.wit.voguely.remote

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.wit.voguely.model.Cart
import com.wit.voguely.model.CartResponse
import com.wit.voguely.ui.main.cart.CartItem
import kotlinx.coroutines.tasks.await

class DeleteCartItem {

    suspend fun deleteItemFromCart(id: String){
        val database = FirebaseDatabase.getInstance("https://voguely-2f691-default-rtdb.europe-west1.firebasedatabase.app")
        val userId = Firebase.auth.currentUser?.uid ?: return
        val cartInDB  = userId.let { database.getReference("carts").child(userId) }

        val carts :List<Cart> = cartInDB.get().await().children.mapNotNull {
            val cartResponse = it.getValue(CartResponse::class.java)  ?: return@mapNotNull null
            val key = it.key ?: return@mapNotNull null
            Cart(key, cartResponse)
        }

        val itemInCartToRemove : Cart? = carts.firstOrNull { it.response.productId == id }



        //COMMENT is cancelling directly the whole amount of the same item =>
        // itemInCartToRemove?.key?.let { cartInDB.child(it).removeValue().await() }

//COMMENT is decreasing the quantity of item in the cart and cancels the items when their quantity is 1
      if (itemInCartToRemove != null) {
            cartInDB?.child(itemInCartToRemove.key)
            ?.setValue(CartResponse(id, itemInCartToRemove.response.quantity - 1))?.await()
             if(itemInCartToRemove.response.quantity == 1){
                 itemInCartToRemove?.key?.let { cartInDB.child(it).removeValue().await() }
             }
        }


    }
}