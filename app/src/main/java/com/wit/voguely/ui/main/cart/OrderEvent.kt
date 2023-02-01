package com.wit.voguely.ui.main.cart

sealed class OrderEvent {

    class OrderConfirmed(val orderMessage: String?): OrderEvent()
    class OrderFailed (val localizedMessage: String?): OrderEvent()
}