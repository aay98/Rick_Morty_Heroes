package com.example.rickandmortyheroes.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortyheroes.retrofit.RetrofitGetResponse

class MyViewModel(application: Application) : AndroidViewModel(application) {
    var nameLiveData = MutableLiveData<List<String>>()
    var speciesLiveData = MutableLiveData<List<String>>()
    var genderLiveData = MutableLiveData<List<String>>()
    var avatarLiveData = MutableLiveData<List<String>>()
    var statusLiveData = MutableLiveData<List<String>>()
    var lastLocLiveData = MutableLiveData<List<String>>()
    var episodesLiveData = MutableLiveData<List<String>>()
    var errorLiveData = MutableLiveData<String>()

    fun getData() {
        RetrofitGetResponse().getResponse(errorLiveData) { heroes ->
            if (heroes == null)
                errorLiveData.value = errorResponse
            else {
                val size = heroes.info.count - 1
                Log.d("MyTag", "getData is working, there are $size heroes")
                Log.d("MyTag", "first is ${heroes.results[0].name}")

                val dataName = mutableListOf<String>()
                val dataSpecies = mutableListOf<String>()
                val dataGender = mutableListOf<String>()
                val dataAvatar = mutableListOf<String>()
                val dataStatus = mutableListOf<String>()
                val dataLastLoc = mutableListOf<String>()
                val dataEpisodes = mutableListOf<String>()

                val pages = heroes.info.pages
                (0..pages).forEach { i ->
                    RetrofitGetResponse().getResponseByPages(errorLiveData, i) { info ->
                        Log.d("MyTag", "page #$i")
                        if (info == null)
                            errorLiveData.value = errorResponse + i.toString()
                        else {
                            Log.d("MyTag", "from ${info.results.size - 20} to ${info.results.size}")
                            (info.results.size - 20..info.results.size).forEach { j ->
                                dataName.add(info.results[j].name)
                                /*
                                dataSpecies.add(info.results[j].species)
                                dataGender.add(info.results[j].gender)
                                dataAvatar.add(info.results[j].image)
                                dataStatus.add(info.results[j].status)
                                dataLastLoc.add(info.results[j].location.name)
                                dataEpisodes.add(info.results[j].episode.size.toString())

                                 */
                            }
                        }
                    }
                }
                nameLiveData.value = dataName
                /*speciesLiveData.value = dataSpecies
                genderLiveData.value = dataGender
                avatarLiveData.value = dataAvatar
                statusLiveData.value = dataStatus
                lastLocLiveData.value = dataLastLoc
                episodesLiveData.value = dataEpisodes

                 */
            }
        }
    }
    companion object {
        private const val errorResponse = "Error response"
        private const val errorLocation = "Error in getting location"
        private const val errorInPermission = "No permission granted"
    }
}
