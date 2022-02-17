package com.example.anymindtest.viewmodel

import android.app.Application
import android.net.Uri
import android.os.PatternMatcher
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddWorkExperienceViewModel(application: Application):BaseViewModel(application) {

    val companyName=MutableLiveData<String>()

    val startDate=MutableLiveData<String>()

    val endDate=MutableLiveData<String>()

    val isCurrentRole=MutableLiveData<Boolean>()



    /*
    * function for check validation
    * */
    fun isDataValid():Boolean{
        var isValid=true

        if(companyName.value.isNullOrEmpty())
        {
            errorString.value="Please enter company name"
            isValid=false
        }
        else if(startDate.value.isNullOrEmpty())
        {
            errorString.value="Please enter start date"
            isValid=false
        }
        else if(isCurrentRole.value!=null&&!isCurrentRole.value!!&&endDate.value.isNullOrEmpty())
        {
            errorString.value="Please enter end date"
            isValid=false
        }



        return isValid
    }



    fun onNextClick()
    {
        if (isDataValid())
        {
            navigateToNextScreen.value=true
        }
    }
}