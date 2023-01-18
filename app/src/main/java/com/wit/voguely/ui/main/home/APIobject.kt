package com.wit.voguely.ui.main.home

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API{
    const val BASE_URL = "...../"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: APIinterface = retrofit.create(APIinterface::class.java)
}