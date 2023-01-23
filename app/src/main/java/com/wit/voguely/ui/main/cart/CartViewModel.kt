package com.wit.voguely.ui.main.cart

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.ui.main.home.Product
import com.wit.voguely.ui.main.home.mockData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private var _itemsInCart = MutableStateFlow<List<Product>>(emptyList())
    val itemsInCart : StateFlow<List<Product>> = _itemsInCart

    private var _displayEmptyCart = MutableStateFlow(false)
    val displayEmptyCart : StateFlow<Boolean> = _displayEmptyCart

    private var _totalPrice = MutableStateFlow<String>("")
    val totalPrice : StateFlow<String> = _totalPrice


    private var _quantityOfSelectedItems = MutableStateFlow<String>("")
    val quantityOfSelectedItems : StateFlow<String> = _quantityOfSelectedItems

    val selectedItems : List<Product> = listOf()

    init{
        loadCart()
    }
    fun loadCart(){
        viewModelScope.launch {
            _itemsInCart.update { mockData }
            _displayEmptyCart.value = false

        }

    }


  /* fun addItemToCart(){

        viewModelScope.launch {
            _displayEmptyCart.value = true
            _totalPrice.value = ""
            _quantityOfSelectedItems.value = ""

            if(selectedItems.isEmpty()){
                //when there are no selected items, show ann empty list
                _itemsInCart.update{
                    emptyList()
                }
            }else{
                //when there are selected items
                _itemsInCart.update{selectedItems}

                _displayEmptyCart.value = false

                _quantityOfSelectedItems.value = ""
                }
            }

            }

   fun totalPriceCalculation(){
        for (item in selectedItems){
            val itemPrice = item.price.toInt()
            _totalPrice.value += itemPrice.toString()
        }

        } */

    fun cancelItemFromCart(){

    }




    fun buyItemsInCart() {
       // Toast.makeText(this, "I am buying items", Toast.LENGTH_SHORT).show()
    }
}