package com.example.rickandmortyheroes.retrofit

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitGetResponse {
    private val baseUrl =
        "http://api.openweathermap.org/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service: HeroesService = retrofit.create(HeroesService::class.java)
    fun getCharacter(errorLiveData: MutableLiveData<String>){
        val call = service.getAllHaracters()
    }
}