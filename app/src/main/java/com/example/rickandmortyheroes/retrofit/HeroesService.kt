package com.example.rickandmortyheroes.retrofit

import Json4Kotlin_Base
import Results
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface HeroesService {

    @GET("character/")
    fun getAllCharacters(
    ): Call<Json4Kotlin_Base>

    @GET("character/?")
    fun getByPages(@Query("pages") pages: Int,
    ): Call<Json4Kotlin_Base>

    @GET("character/{id}/")
    fun getInfoIf(@Path("id") id: Int): Call<Results>

}