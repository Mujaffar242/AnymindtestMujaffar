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
import com.example.anymindtest.model.EducationModel
import com.example.anymindtest.model.PersonalInfoModel
import com.example.anymindtest.model.WorkExperienceModel
import com.example.anymindtest.repository.ResumeDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddEducationViewModel(application: Application):BaseViewModel(application) {

    val className=MutableLiveData<String>()

    val passingYear=MutableLiveData<String>()

    val percentage=MutableLiveData<String>()


    val database= getDatabase(application)

    val educationModelLiveData=MutableLiveData<EducationModel>()



    /*
   * save education data into local database
   * */
    fun saveUpdateEducationInfo()
    {
        val educationModel=EducationModel(className.value!!,passingYear.value!!,percentage.value!!)



        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                if (educationModelLiveData.value?.id!=null&&educationModelLiveData.value?.id!!>0)
                {
                    educationModel.id=educationModelLiveData.value?.id!!
                    database.educationDAO.update(educationModel)
                }
                else
                    database.educationDAO.insertSinglevalue(educationModel)
            }
            navigateToNextScreen.value=true
        }
    }


    /*
    * get data from room database
    * */
    fun getWorkEducationData(id:Int)
    {
        viewModelScope.launch {

            withContext(Dispatchers.IO)
            {
                val data=database.educationDAO.getSingleValue(id.toString())
                withContext(Dispatchers.Main)
                {
                    if(data!=null)
                    {
                        educationModelLiveData.value=data
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

        if(className.value.isNullOrEmpty())
        {
            errorString.value="Please enter class name"
            isValid=false
        }
        else if(passingYear.value.isNullOrEmpty())
        {
            errorString.value="Please passing year"
            isValid=false
        }
        else if(percentage.value.isNullOrEmpty())
        {
            errorString.value="Please enter percentage"
            isValid=false
        }



        return isValid
    }



    fun onNextClick()
    {
        if (isDataValid())
        {
            viewModelScope.launch {
                saveUpdateEducationInfo()
            }
            navigateToNextScreen.value=true
        }
    }
}