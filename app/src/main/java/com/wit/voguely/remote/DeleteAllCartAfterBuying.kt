package com.wit.voguely.remote

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.wit.voguely.DATABASE_URL
import com.wit.voguely.model.Cart
import com.wit.voguely.model.CartResponse
import kotlinx.coroutines.tasks.await

class DeleteAllCartAfterBuying () {
    suspend fun deleteItemFromCart() {
        val database = FirebaseDatabase.getInstance(DATABASE_URL)
        val userId = Firebase.auth.currentUser?.uid ?: return
        val cartInDB = database.getReference("carts").child(userId)
        cartInDB.removeValue().await()
}}