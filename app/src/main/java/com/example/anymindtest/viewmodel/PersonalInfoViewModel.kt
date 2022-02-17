package com.example.anymindtest.viewmodel

import android.app.Application
import android.net.Uri
import android.os.PatternMatcher
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anymindtest.database.getDatabase
import com.example.anymindtest.model.PersonalInfoModel
import com.example.anymindtest.repository.ResumeDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PersonalInfoViewModel(application: Application):BaseViewModel(application) {

    val imagePath=MutableLiveData<Uri>()

    val email=MutableLiveData<String>()

    val mobile=MutableLiveData<String>()

    val address=MutableLiveData<String>()

    val careerObjective=MutableLiveData<String>()

    val yearOfExperience=MutableLiveData<String>()

    val database= getDatabase(application)


    val personalInfoModel=MutableLiveData<PersonalInfoModel>()



    /*
    * save personal data in room database
    * */
    fun savePersonalInfo()
    {
        val personalInfoModel=PersonalInfoModel(imagePath.value.toString(),email.value!!,mobile.value!!,address.value!!,careerObjective.value!!,yearOfExperience.value!!)

        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                database.personalInfoDAO.insertSinglevalue(personalInfoModel)
            }
          //  navigateToNextScreen.value=true
        }
    }


    /*
    * get data from room database
    * */
    fun getPersonalInfoFromRoom()
    {
        viewModelScope.launch {

            withContext(Dispatchers.IO)
            {
                val data=database.personalInfoDAO.getSingleValue(1)
                withContext(Dispatchers.Main)
                {
                    if(data!=null)
                    {
                        personalInfoModel.value=data
                    }
                }

            }


        }
    }


    /*
    * function for check validation
    * */
    fun isDataValid():Boolean{
        var isValid=true

        if(imagePath.value==null)
        {
            errorString.value="Please select image"
        }
        else if(email.value.isNullOrEmpty())
        {
            errorString.value="Please enter email"
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email.value).matches())
        {
            errorString.value= "Please valid email"

        }
        else if(mobile.value.isNullOrEmpty())
        {
            errorString.value="Please enter mobile number"
        }
        else if(address.value.isNullOrEmpty())
        {
            errorString.value="Please enter Residence address"
        }
        else if(careerObjective.value.isNullOrEmpty())
        {
            errorString.value="Please enter career objective"
        }
        else if(yearOfExperience.value.isNullOrEmpty())
        {
            errorString.value="Please enter year of experience"
        }

        return isValid
    }



    fun onNextClick()
    {
        savePersonalInfo()
    }
}