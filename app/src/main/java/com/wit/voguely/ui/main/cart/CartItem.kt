package com.wit.voguely.ui.main.cart

import com.wit.voguely.ui.main.home.Product

data class CartItem(var product: Product, var quantity: Int = 0) {

   // val pricePerKindOfItem = quantity * product.price

}