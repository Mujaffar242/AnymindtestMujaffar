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
import com.example.anymindtest.model.ProjectModel
import com.example.anymindtest.model.WorkExperienceModel
import com.example.anymindtest.repository.ResumeDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddProjectViewModel(application: Application):BaseViewModel(application) {

    val projectName=MutableLiveData<String>()

    val teamSize=MutableLiveData<String>()

    val projectSummary=MutableLiveData<String>()

    val technologyused=MutableLiveData<String>()

    val role=MutableLiveData<String>()


    val database= getDatabase(application)

    val projectModelLiveData=MutableLiveData<ProjectModel>()



    /*
   * save project data into local database
   * */
    fun saveUpdateProjectInfo()
    {
        val projectModel=ProjectModel(projectName.value!!,teamSize.value!!,projectSummary.value!!,technologyused.value!!,role.value!!)

        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                if (projectModelLiveData.value?.id!=null&&projectModelLiveData.value?.id!!>0)
                    database.projectDAO.update(projectModel)
                else
                    database.projectDAO.insertSinglevalue(projectModel)
            }
            navigateToNextScreen.value=true
        }
    }


    /*
    * get data from room database
    * */
    fun getProjectData(id:Int)
    {
        viewModelScope.launch {

            withContext(Dispatchers.IO)
            {
                val data=database.projectDAO.getSingleValue(id.toString())
                withContext(Dispatchers.Main)
                {
                    if(data!=null)
                    {
                        projectModelLiveData.value=data
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

        if(projectName.value.isNullOrEmpty())
        {
            errorString.value="Please enter project name"
            isValid=false
        }
        else if(teamSize.value.isNullOrEmpty())
        {
            errorString.value="Please enter team size"
            isValid=false
        }
        else if(projectSummary.value.isNullOrEmpty())
        {
            errorString.value="Please project summery"
            isValid=false
        }
        else if(technologyused.value.isNullOrEmpty())
        {
            errorString.value="Please enter technology used"
            isValid=false
        }
        else if(role.value.isNullOrEmpty())
        {
            errorString.value="Please enter role"
            isValid=false
        }



        return isValid
    }



    fun onNextClick()
    {
        if (isDataValid())
        {
            viewModelScope.launch {
                saveUpdateProjectInfo()
            }
            navigateToNextScreen.value=true
        }
    }
}