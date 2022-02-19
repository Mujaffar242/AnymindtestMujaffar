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
import com.example.anymindtest.model.WorkExperienceModel
import com.example.anymindtest.repository.ResumeDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddWorkExperienceViewModel(application: Application):BaseViewModel(application) {

    val companyName=MutableLiveData<String>()

    val startDate=MutableLiveData<String>()

    val endDate=MutableLiveData<String>()

    val isCurrentRole=MutableLiveData<Boolean>()

    val database= getDatabase(application)

    val workExperienceLiveData=MutableLiveData<WorkExperienceModel>()



    /*
   * save experiene data into local database
   * */
    fun saveUpdateEducationInfo()
    {
        val experienceModel=WorkExperienceModel(companyName.value!!,startDate.value!!,endDate.value!!)

        experienceModel.id=workExperienceLiveData.value?.id!!

        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                if (workExperienceLiveData.value?.id!=null&&workExperienceLiveData.value?.id!!>0)
                    database.workExperienceDAO.update(experienceModel)
                else
                    database.workExperienceDAO.insertSinglevalue(experienceModel)
            }
            navigateToNextScreen.value=true
        }
    }


    /*
    * get data from room database
    * */
    fun getWorkExperienceData(id:Int)
    {
        viewModelScope.launch {

            var data=null
            withContext(Dispatchers.IO)
            {
                val data=database.workExperienceDAO.getSingleValue(id.toString())
                workExperienceLiveData.postValue(data!!)

            }

        }
    }


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
            viewModelScope.launch {
                withContext(Dispatchers.IO)
                {
                    saveUpdateEducationInfo()
                }
               // navigateToNextScreen.value=true
            }
        }
    }
}