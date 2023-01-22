package com.wit.voguely.ui.main.cart

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.ui.main.home.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private var _clickedItemsForCart = MutableStateFlow<List<Product>>(listOf())
    val clickedItemsForCart : StateFlow<List<Product>> = _clickedItemsForCart

    private var _displayEmptyCart = MutableStateFlow(false)
    val displayEmptyCart : StateFlow<Boolean> = _displayEmptyCart

    private val _totalPrice = MutableStateFlow<String>("")
    val totalPrice : StateFlow<String> = _totalPrice

    private var _quantityOfSelectedItems = MutableStateFlow<String>("")
    val quantityOfSelectedItems : StateFlow<String> = _quantityOfSelectedItems





    fun addItemToCart(){
        _displayEmptyCart.value = true
        _totalPrice.value = ""
        _quantityOfSelectedItems.value = ""

        viewModelScope.launch {
            //vala data = API.api.getRandomItem()

            //if(_clickedItemsForCart.isEmpty()){
              //  _clickedItemsForCart
            }

        }

    fun cancelItemFromCart(){

    }

    fun totalPriceCalculation(){

    }


    fun buyItemsInCart() {
       // Toast.makeText(this, "I am buying items", Toast.LENGTH_SHORT).show()
    }
}