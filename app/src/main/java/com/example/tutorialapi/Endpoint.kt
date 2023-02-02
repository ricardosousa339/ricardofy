package com.example.tutorialapi

import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {

    @GET("listamusicas")
    fun getPosts() : Call<List<Musica>>
}