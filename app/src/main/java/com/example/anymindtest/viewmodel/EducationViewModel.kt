package com.example.anymindtest.viewmodel

import android.app.Application
import android.net.Uri
import android.os.PatternMatcher
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anymindtest.database.getDatabase
import com.example.anymindtest.model.EducationModel
import com.example.anymindtest.model.PersonalInfoModel
import com.example.anymindtest.model.ProjectModel
import com.example.anymindtest.model.WorkExperienceModel
import com.example.anymindtest.repository.ResumeDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EducationViewModel(application: Application):BaseViewModel(application) {

    val database= getDatabase(application)

    val resumeDataRepository=ResumeDataRepository(database)


   private val _educationData=resumeDataRepository.educationHistoryList
    val educationData:LiveData<List<EducationModel>>
    get() = _educationData

    fun onNextClick()
    {
              navigateToNextScreen.value=true

    }
}