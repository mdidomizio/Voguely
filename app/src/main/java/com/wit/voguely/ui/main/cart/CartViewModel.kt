package com.wit.voguely.ui.main.cart

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.remote.DeleteCartItem
import com.wit.voguely.remote.GetCartDataSource
import com.wit.voguely.ui.main.home.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class CartViewModel : ViewModel() {

    private val getCartDataSource = GetCartDataSource()
    private val deleteCartItem = DeleteCartItem()

    private var _itemsInCart = MutableStateFlow<List<CartItem>>(emptyList())
    val itemsInCart : StateFlow<List<CartItem>> = _itemsInCart

    private var _displayEmptyCart = MutableStateFlow(true)
    val displayEmptyCart : StateFlow<Boolean> = _displayEmptyCart


    fun loadCartItems(){
        _displayEmptyCart.value = true
        viewModelScope.launch {
            val cart = getCartDataSource.getItemInCart()
            _itemsInCart.update { cart }
            _displayEmptyCart.value = false
        }
    }
    fun getTotalPrice(item: List<CartItem>) : Int {
            val totalPrice = item.sumOf{
                it.product.price * it.quantity }
        return totalPrice
    }

    fun deleteItemFromCart(id: String){
        viewModelScope.launch(Dispatchers.IO){
          deleteCartItem.deleteItemFromCart(id)
            loadCartItems()
        }
    }



    fun buyItemsInCart(){}



}