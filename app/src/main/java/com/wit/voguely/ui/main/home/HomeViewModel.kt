package com.wit.voguely.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.remote.AddToCartDataSource
import com.wit.voguely.remote.ProductsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val productsDataSource = ProductsDataSource()
    private val addToCartDataSource = AddToCartDataSource()

    private val _dataProduct = MutableStateFlow <List<Product>>(listOf())
    val dataProduct :StateFlow<List<Product>> = _dataProduct.asStateFlow()

    private val _displayProgressBar = MutableStateFlow(false)
    val displayProgressBar : StateFlow<Boolean> = _displayProgressBar

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
            _dataProduct.value.let{
                addToCartDataSource.addItemToCart(product.id)
            }
        }
    }


}