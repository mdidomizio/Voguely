package com.wit.voguely.ui.main.home

sealed class AddToCartEvent {
    class AddToCartSuccessful(val successMessage: String = "The product has been successfully added to the cart"): AddToCartEvent()
    class AddToCartFailed(val failedMessagge : String = "OPS...Something went wrong with adding the product to the cart" ) : AddToCartEvent()
}