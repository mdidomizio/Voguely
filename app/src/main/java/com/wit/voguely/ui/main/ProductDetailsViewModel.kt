package com.wit.voguely.ui.main

import android.provider.SyncStateContract.Helpers.update
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.remote.ProductDataSource
import com.wit.voguely.remote.ProductsDataSource
import com.wit.voguely.ui.main.home.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailsViewModel : ViewModel() {

    private val productDataSource = ProductDataSource()

    private val _dataProduct = MutableStateFlow <Product?>(null)
    val dataProduct : StateFlow <Product?> = _dataProduct.asStateFlow()


    fun loadDetails( id: String){
        viewModelScope.launch {
            _dataProduct.update{productDataSource.getProduct(id)}
        }

    }


}