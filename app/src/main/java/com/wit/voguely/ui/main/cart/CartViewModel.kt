package com.wit.voguely.ui.main.cart

import android.security.identity.AccessControlProfileId
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.remote.DeleteAllCartAfterBuying
import com.wit.voguely.remote.DeleteCartItem
import com.wit.voguely.remote.GetCartDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.security.KeyStore.TrustedCertificateEntry

class CartViewModel : ViewModel() {

    private val getCartDataSource = GetCartDataSource()
    private val deleteCartItem = DeleteCartItem()
    private val deleteAllCartAfterBuying = DeleteAllCartAfterBuying()

    private var _itemsInCart = MutableStateFlow<List<CartItem>>(emptyList())
    val itemsInCart : StateFlow<List<CartItem>> = _itemsInCart

    private var _displayEmptyCart = MutableStateFlow(true)
    val displayEmptyCart : StateFlow<Boolean> = _displayEmptyCart

   /* private var _displayOrderSuccessful = MutableStateFlow(false)
    val displayOrderSuccessful : StateFlow<Boolean> = _displayOrderSuccessful

    private var _displayFullCart = MutableStateFlow(false)
    val displayFullCart : StateFlow<Boolean> = _displayFullCart
    */



    fun loadCartItems(){

        viewModelScope.launch {
            _displayEmptyCart.value = true
           // _displayOrderSuccessful.value = false
           // _displayFullCart.value = false
            val cart = getCartDataSource.getItemInCart()
            if(cart.isEmpty()){
                _displayEmptyCart.value = true
               // _displayOrderSuccessful.value = false
                _itemsInCart.value = emptyList()
                return@launch
            }else{
                _itemsInCart.update { cart }
                if (cart.isNotEmpty()){
                    _displayEmptyCart.value = false
                    //_displayOrderSuccessful.value = false
                    //_displayFullCart.value = true
                }

            }
        }
    }

    fun getTotalPrice(item: List<CartItem>) : Int {
            val totalPrice = item.sumOf{
                it.product.price * it.quantity }
        return totalPrice
    }

    fun deleteItemFromCart(id: String){
        viewModelScope.launch(Dispatchers.IO){
                _displayEmptyCart.value = false
               // _displayOrderSuccessful.value = false

          deleteCartItem.deleteItemFromCart(id)
            loadCartItems()

        }
    }



    fun buyItemsInCart() {
        viewModelScope.launch (Dispatchers.IO){
            deleteAllCartAfterBuying.deleteItemFromCart()
            _displayEmptyCart.value = true
        }

    }



}