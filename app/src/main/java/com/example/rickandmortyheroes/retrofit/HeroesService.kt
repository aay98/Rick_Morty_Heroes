package com.example.rickandmortyheroes.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesService {
    @GET("data/api/character")
    fun getAllHaracters(
    ): Call<HeroesResponse>

}