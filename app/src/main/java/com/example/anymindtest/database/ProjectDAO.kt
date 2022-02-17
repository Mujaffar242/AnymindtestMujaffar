package com.example.anymindtest.database
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.anymindtest.model.PersonalInfoModel
import com.example.anymindtest.model.ProjectModel
import com.example.anymindtest.model.WorkExperienceModel

/*
* room query for communicate with sqlite database
* */
@Dao
interface ProjectDAO {

    @Insert()
    fun insertSinglevalue(projectModel: ProjectModel)

    @Query("SELECT * FROM projectmodel WHERE id = :id")
    fun getSingleValue(id:String):ProjectModel


    @Query("select * from projectmodel")
    fun getAllProjects():LiveData<List<ProjectModel>>

    /*
   *for update database model single row
   * use for update task complete or not
    */
    @Update
    fun update(projectModel: ProjectModel)


}

