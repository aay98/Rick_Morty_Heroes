package com.example.rickandmortyheroes.retrofit

import Json4Kotlin_Base
import Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesService {
    @GET("data/api/character")
    fun getAllCharacters(
    ): Call<Json4Kotlin_Base>
    @GET("data/api/character/")
    fun getInfo(
        @Query("id") id: String
    ): Call<Results>

}