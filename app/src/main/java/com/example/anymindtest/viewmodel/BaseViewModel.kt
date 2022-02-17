package com.example.anymindtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel(application: Application): AndroidViewModel(application) {
    val errorString= MutableLiveData<String>()

    val navigateToNextScreen= MutableLiveData<Boolean>()



    fun resetNavigateToNextScreen()
    {
        navigateToNextScreen.value=false
    }
}