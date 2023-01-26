package com.wit.voguely.remote

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.voguely.ui.main.home.Product
import kotlinx.coroutines.tasks.await

class ProductDataSource  {
    suspend fun getProduct(id: String) : Product? {
        val result = Firebase.database("https://voguely-2f691-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference("products")
            .get()
            .await()

        return result.child(id).getValue(Product::class.java)

    }
}