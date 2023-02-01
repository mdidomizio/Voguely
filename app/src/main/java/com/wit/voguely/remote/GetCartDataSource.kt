package com.wit.voguely.remote

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.wit.voguely.DATABASE_URL
import com.wit.voguely.model.Cart
import com.wit.voguely.model.CartResponse
import com.wit.voguely.ui.main.cart.CartItem
import kotlinx.coroutines.tasks.await

class GetCartDataSource {

    private val productDataSource = ProductDataSource()


    suspend fun getItemInCart(): List<CartItem> {
        val database = FirebaseDatabase.getInstance(DATABASE_URL)
        val userId = Firebase.auth.currentUser?.uid ?: return emptyList()

        val cartRef = database.getReference("carts").child(userId)
            .get()
            .await()
        return cartRef.children.mapNotNull {
            val cartResponse = it.getValue(CartResponse::class.java) ?: return@mapNotNull null
            val product = productDataSource.getProduct(cartResponse.productId) ?: return@mapNotNull null
            CartItem(product, cartResponse.quantity)
        }

    }
}