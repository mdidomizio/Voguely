package com.wit.voguely.remote

import com.google.android.gms.tasks.Tasks.await
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.voguely.model.CartResponse
import com.wit.voguely.ui.main.home.Product
import kotlinx.coroutines.tasks.await

class AddToCartDataSource {

    suspend fun addItemToCart(id: String) {
        val result = Firebase.database("https://voguely-2f691-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference("carts")
            .child(Firebase.auth.currentUser!!.uid)
            .push().setValue(CartResponse(id, 1 ) )
            .await()

    }
}




























      /*  val database = FirebaseDatabase.getInstance()
        val userId = Firebase.auth.currentUser?.uid?: return

        val cartRef = database.getReference("cart").child(userId)

        val carts = cartRef.get().await().children.mapNotNull {
            val cartResponse = it.getValue(CartResponse ::class.java) ?: return@mapNotNull null
            val key = it.key ?: return@mapNotNull null
            Cart(key, cartResponse)
        }
        val cartResponse = carts.firstOrNull{ it.response.productId == id }

        if(cartResponse == null){
            cartRef.push().setValue(CartResponse(id, 1)).await()
        }else{
            cartRef.child(cartResponse.key)
                .setValue(CartResponse(id, cartResponse.response.quantity + 1)).await()
        }

    }*/
