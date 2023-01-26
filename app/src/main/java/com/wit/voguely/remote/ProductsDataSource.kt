package com.wit.voguely.remote

import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.voguely.ui.main.home.Product
import kotlinx.coroutines.tasks.await

class ProductsDataSource {
    suspend fun getProducts():List<Product>{
        try{
            val result = Firebase.database("https://voguely-2f691-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("products")
                .get()
                .await()

            return result.children.mapNotNull{it.getValue(Product::class.java)}

        }catch (e:Exception){
            Log.d("TAG123", e.toString())
            return emptyList()
        }

    }

}