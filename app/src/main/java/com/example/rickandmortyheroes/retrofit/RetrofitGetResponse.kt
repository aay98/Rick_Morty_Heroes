package com.example.rickandmortyheroes.retrofit

import Json4Kotlin_Base
import Results
import android.util.Log
import androidx.lifecycle.MutableLiveData
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitGetResponse {
    private val baseUrl =
        "https://rickandmortyapi.com/api/"

    val okHttpClient = provideOkHttpClientBuilder().build()
    private fun provideOkHttpClientBuilder() = OkHttpClient.Builder().apply {
    connectTimeout(30, TimeUnit.SECONDS)
    readTimeout(30, TimeUnit.SECONDS)
    writeTimeout(30, TimeUnit.SECONDS)
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
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
                Log.d("MyTag", "resp is ${response.body()}")
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
    fun getResponseByPages(errorLiveData: MutableLiveData<String>,id : Int,callback: (oneCall: Json4Kotlin_Base?) -> Unit){
        val call = service.getByPages(id)
        call.enqueue(object : Callback<Json4Kotlin_Base> {
            override fun onResponse(
                call: Call<Json4Kotlin_Base>?,
                response: Response<Json4Kotlin_Base>?
            ) {
                Log.d("MyTag", "resp cod is ${response!!.code()}")
                Log.d("MyTag", "resp is ${response.body()}")
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
    fun getResponseInfo(errorLiveData: MutableLiveData<String>, id : Int, callback: (oneCall: Results?) -> Unit){
        val call = service.getInfoIf(id)
        call.enqueue(object : Callback<Results> {
            override fun onResponse(
                call: Call<Results>?,
                response: Response<Results>?
            ) {
                Log.d("MyTag", "resp cod is ${response!!.code()}")
                Log.d("MyTag", "resp is ${response.body()}")
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