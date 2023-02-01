package com.wit.voguely.ui.main

import android.provider.SyncStateContract.Helpers.update
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.remote.AddToCartDataSource
import com.wit.voguely.remote.ProductDataSource
import com.wit.voguely.remote.ProductsDataSource
import com.wit.voguely.ui.main.home.AddToCartEvent
import com.wit.voguely.ui.main.home.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProductDetailsViewModel : ViewModel() {

    private val productDataSource = ProductDataSource()
    private val addToCartDataSource = AddToCartDataSource()

    private val _dataProduct = MutableStateFlow<Product?>(null)
    val dataProduct: StateFlow<Product?> = _dataProduct.asStateFlow()

    private val _event = MutableSharedFlow<AddToCartEvent>()
    val event = _event.asSharedFlow()


    fun loadDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _dataProduct.update { productDataSource.getProduct(id) }
        }
    }

    fun addToCart() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val product = _dataProduct.value
                if (product != null){
                    addToCartDataSource.addItemToCart(product.id)
                    _event.emit(AddToCartEvent.AddToCartSuccessful("Item added to cart"))
                }
            } catch (e: Exception) {
                _event.emit(AddToCartEvent.AddToCartFailed(e.localizedMessage))
            }
        }
    }


}