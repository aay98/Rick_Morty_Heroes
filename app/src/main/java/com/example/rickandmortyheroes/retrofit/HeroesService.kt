package com.example.rickandmortyheroes.retrofit

import Json4Kotlin_Base
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesService {
    @GET("data/api/character")
    fun getAllCharacters(
    ): Call<Json4Kotlin_Base>

}