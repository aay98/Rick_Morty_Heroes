package com.example.rickandmortyheroes.model

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MyViewModel(application: Application) : AndroidViewModel(application) {
    var nameLiveData = MutableLiveData<String>()
    var speciesLiveData = MutableLiveData<String>()
    var genderLiveData = MutableLiveData<String>()
    var errorLiveData = MutableLiveData<String>()


}