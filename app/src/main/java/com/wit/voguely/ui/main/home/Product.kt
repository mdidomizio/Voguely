package com.wit.voguely.ui.main.home

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Product (
    var name: String = "",
    var price: Int = 0,
    var image: String = "",
    var currency: String = "",
    var description: String = "",
    var rating: Double = 0.0,
    var reviews: Int = 0,
    var id: String = ""
)

