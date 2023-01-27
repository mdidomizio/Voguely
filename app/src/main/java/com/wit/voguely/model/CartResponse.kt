package com.wit.voguely.model

import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties

data class CartResponse (
    val ProductId: String = "",
    val quantity : Int = 0
        )
