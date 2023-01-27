package com.wit.voguely.remote

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class AddToCartDataSource {
    suspend fun addItemToCart (id:String){
        val database = FirebaseDatabase.getInstance()
        val userId = Firebase.auth.currentUser?.uid?: return
        val cartRef = database.getReference(CARTS).child(userId)

    }
}