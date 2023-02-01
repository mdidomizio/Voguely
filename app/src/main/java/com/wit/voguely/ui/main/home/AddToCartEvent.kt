package com.wit.voguely.ui.main.home

sealed class AddToCartEvent {
    class AddToCartSuccessful(val cartMessage: String): AddToCartEvent()
    class AddToCartFailed(val localizedMessage: String?) : AddToCartEvent()
}