package com.wit.voguely.remote

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.voguely.ui.main.home.Product
import kotlinx.coroutines.tasks.await

class ProductsDataSource {
    suspend fun getProducts():List<Product>{
        val result = Firebase.database
            .getReference("products")
            .get()
            .await()

        return result.children.mapNotNull{it.getValue(Product::class.java)}
    }

}