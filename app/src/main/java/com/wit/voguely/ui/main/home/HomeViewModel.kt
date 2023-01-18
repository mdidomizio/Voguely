package com.wit.voguely.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val mockProduct = Product (
        "https://resource.logitechg.com/w_692,c_limit,q_auto,f_auto,dpr_1.0/d_transparent.gif/content/dam/gaming/en/products/audio/g735-wireless-headset/gallery/g735-gallery-1.png?v=1",
        "AirPods Max",
        "629,00 €",
        "3.456",
        "14.787")
    private val _dataProduct = MutableStateFlow <List<Product>>(listOf())
    val dataProduct :StateFlow<List<Product>> = _dataProduct.asStateFlow()

    private val _displayProgressBar = MutableStateFlow(false)
    val displayProgressBar : StateFlow<Boolean> = _displayProgressBar


    init {
        loadData(mockProduct)
    }

    fun loadData(mockProduct : Product){
        _displayProgressBar.value = true
        viewModelScope.launch{
            _dataProduct.update{listOf(mockProduct)}

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