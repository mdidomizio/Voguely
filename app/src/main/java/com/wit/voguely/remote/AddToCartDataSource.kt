package com.wit.voguely.remote

import android.content.ClipData
import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.voguely.DATABASE_URL
import com.wit.voguely.model.Cart
import com.wit.voguely.model.CartResponse
import com.wit.voguely.ui.main.home.Product
import kotlinx.coroutines.tasks.await


class AddToCartDataSource() {

    suspend fun addItemToCart(id: String) {
        val database = FirebaseDatabase.getInstance(DATABASE_URL)
        val userId = Firebase.auth.currentUser?.uid ?: return
        val cartInDB = database.getReference("carts").child(userId)

        val carts = cartInDB.get().await().children.mapNotNull {
            val cartResponse = it.getValue(CartResponse::class.java) ?: return@mapNotNull null
            val key = it.key ?: return@mapNotNull null
            Cart(key, cartResponse)
        }

        val itemInCartToCheck = carts.firstOrNull { it.response.productId == id }

        if (itemInCartToCheck == null) {
            cartInDB.push().setValue(CartResponse(id, 1)).await()
        } else {
            cartInDB.child(itemInCartToCheck.key)
                .setValue(CartResponse(id, itemInCartToCheck.response.quantity + 1))
                .await()
        }

    }
}