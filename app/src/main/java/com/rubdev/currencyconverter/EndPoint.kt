package com.rubdev.currencyconverter


import retrofit2.http.GET
import retrofit2.Call

interface EndPoint {

    @GET("USD-BRL")
    fun getDolar() : Call <List<Cotacoes>>
}