package com.example.anymindtest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.anymindtest.model.EducationModel
import com.example.anymindtest.model.PersonalInfoModel
import com.example.anymindtest.model.ProjectModel
import com.example.anymindtest.model.WorkExperienceModel

@Database(entities = [PersonalInfoModel::class,EducationModel::class,WorkExperienceModel::class,ProjectModel::class], version = 1,exportSchema = false)
abstract class ResumeDataDataBase : RoomDatabase() {
    abstract val personalInfoDAO: PersonalInfoDAO
    abstract val educationDAO:EducationDAO
    abstract val projectDAO:ProjectDAO
    abstract val workExperienceDAO:WorkExperienceDAO
}

private lateinit var INSTANCE: ResumeDataDataBase

fun getDatabase(context: Context): ResumeDataDataBase {
    synchronized(ResumeDataDataBase::class.java)
    {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ResumeDataDataBase::class.java,
                "resume"
            ).build()
        }
    }

    return INSTANCE
}