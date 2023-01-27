package com.wit.voguely.ui.main.home

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.remote.AddToCartDataSource
import com.wit.voguely.remote.ProductsDataSource
import com.wit.voguely.ui.login.LoginEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val productsDataSource = ProductsDataSource()
    private val addToCartDataSource = AddToCartDataSource()

    private val _dataProduct = MutableStateFlow <List<Product>>(listOf())
    val dataProduct :StateFlow<List<Product>> = _dataProduct.asStateFlow()

    private val _displayProgressBar = MutableStateFlow(false)
    val displayProgressBar : StateFlow<Boolean> = _displayProgressBar

    private val _event = MutableSharedFlow<AddToCartEvent>()
    val event = _event.asSharedFlow()

    init {
        loadData(emptyList())
    }

    fun loadData(mockData: List<Product>){
        _displayProgressBar.value = true

        viewModelScope.launch{
            _dataProduct.update{ productsDataSource.getProducts() }
            _displayProgressBar.value = false
        }

    }

    fun addToCart (product: Product){
        viewModelScope.launch (Dispatchers.IO) {
            try {
                _dataProduct.value.let{
                    addToCartDataSource.addItemToCart(product.id)
                }
               // _event.emit(AddToCartEvent.AddToCartSuccessful.)
            }catch (e: Exception){
               // _event.emit(AddToCartEvent.AddToCartFailed.)
            }

        }
    }


}