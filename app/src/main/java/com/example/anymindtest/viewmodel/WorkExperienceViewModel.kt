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

class WorkExperienceViewModel(application: Application):BaseViewModel(application) {

   private val _workExperienceData=MutableLiveData<List<WorkExperienceModel>>()
    val workExperienceData:LiveData<List<WorkExperienceModel>>
    get() = _workExperienceData


    val database= getDatabase(application)

    val resumeDataRepository=ResumeDataRepository(database)



    /*
   * get list of data from room databse
   * */
    fun getWorkExperieceData()
    {

        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                _workExperienceData.postValue(resumeDataRepository.workExperienceList.value)

            }
        }
    }




    fun onNextClick()
    {
            viewModelScope.launch {
                withContext(Dispatchers.IO)
                {
                }
            }
            navigateToNextScreen.value=true

    }
}