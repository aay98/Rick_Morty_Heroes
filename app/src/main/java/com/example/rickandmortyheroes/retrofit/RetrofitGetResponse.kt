package com.example.rickandmortyheroes.retrofit

import Json4Kotlin_Base
import Results
import android.util.Log
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
    private val successfulResponseCod = 200
    private val units =
        "metric"
    private val exclude =
        "current,minutely,alerts"
    private val responseError = "Error in response body"
    private val codError = "Error in response cod"

    fun getResponse(errorLiveData: MutableLiveData<String>,callback: (oneCall: Json4Kotlin_Base?) -> Unit){
        val call = service.getAllCharacters()
        call.enqueue(object : Callback<Json4Kotlin_Base> {
            override fun onResponse(
                call: Call<Json4Kotlin_Base>?,
                response: Response<Json4Kotlin_Base>?
            ) {
                Log.d("MyTag", "resp cod is ${response!!.code()}")
                Log.d("MyTag", "resp is $response")
                if (response.code() == successfulResponseCod && response.body() != null) {
                    val returnResponse = response.body()
                    callback(returnResponse)
                } else {
                    if (response.code() == successfulResponseCod)
                        errorLiveData.value = responseError
                    else
                        errorLiveData.value = codError
                    callback(null)
                }
            }

            override fun onFailure(call: Call<Json4Kotlin_Base>?, t: Throwable?) {
                if (t != null) {
                    errorLiveData.value = t.message.toString()
                }
                callback(null)
            }

        })
    }

    fun getResponseInfo(errorLiveData: MutableLiveData<String>,id : String ,callback: (oneCall: Results?) -> Unit){
        val call = service.getInfo(id)
        call.enqueue(object : Callback<Results> {
            override fun onResponse(
                call: Call<Results>?,
                response: Response<Results>?
            ) {
                Log.d("MyTag", "resp cod is ${response!!.code()}")
                Log.d("MyTag", "resp is $response")
                if (response.code() == successfulResponseCod && response.body() != null) {
                    val returnResponse = response.body()
                    callback(returnResponse)
                } else {
                    if (response.code() == successfulResponseCod)
                        errorLiveData.value = responseError
                    else
                        errorLiveData.value = codError
                    callback(null)
                }
            }

            override fun onFailure(call: Call<Results>?, t: Throwable?) {
                if (t != null) {
                    errorLiveData.value = t.message.toString()
                }
                callback(null)
            }

        })
    }
}