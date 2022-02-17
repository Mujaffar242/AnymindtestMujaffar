package com.example.anymindtest.repository

import androidx.lifecycle.LiveData
import com.example.anymindtest.database.PersonalInfoDAO
import com.example.anymindtest.database.ResumeDataDataBase
import com.example.anymindtest.model.EducationModel
import com.example.anymindtest.model.PersonalInfoModel
import com.example.anymindtest.model.ProjectModel
import com.example.anymindtest.model.WorkExperienceModel

class ResumeDataRepository(val database: ResumeDataDataBase) {


    /*
* get list of work experience from room database
*  */
    val personalInfoData: LiveData<List<PersonalInfoModel>> =
        database.personalInfoDAO.getPersonalInfo()


    /*
* get list of work experience from room database
*  */
    val workExperienceList: LiveData<List<WorkExperienceModel>> =
        database.workExperienceDAO.getAllWorkExperience()


    /*
* get list of  education data from room database
*  */
    val educationHistoryList: LiveData<List<EducationModel>> =
        database.educationDAO.getAllEducationList()


    /*
* get list projects from room database
*  */
    val projectList: LiveData<List<ProjectModel>> =
        database.projectDAO.getAllProjects()
}