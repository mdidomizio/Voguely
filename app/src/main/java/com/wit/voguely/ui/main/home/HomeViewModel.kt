package com.wit.voguely.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.remote.ProductsDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val productsDataSource = ProductsDataSource()

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

   /* fun dataLoad() {
        _displayProgressBar.value = true
        viewModelScope.launch{
            //TODO by fetching => viewModelScope.launch(Dispatchers.IO){
            //            val data = API.api.getRandomPic()
            val currentValue = _dataLive.value.toMutableList()
            currentValue.add(_dataLive)
            _dataLive.value = currentValue
            _displayProgressBar.value = false
        }
    }*/
}