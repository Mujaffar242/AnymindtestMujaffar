package com.example.anymindtest.database
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.anymindtest.model.PersonalInfoModel
import com.example.anymindtest.model.WorkExperienceModel

/*
* room query for communicate with sqlite database
* */
@Dao
interface WorkExperienceDAO {

    @Query("SELECT * FROM WorkExperienceModel")
    fun getAllWorkExperience():LiveData<List<WorkExperienceModel>>

    @Insert()
    fun insertSinglevalue(workExperienceModel: WorkExperienceModel)

    @Query("SELECT * FROM workExperienceModel WHERE id = :id")
    fun getSingleValue(id:String):WorkExperienceModel

    /*
   *for update database model single row
   * use for update task complete or not
    */
    @Update
    fun update(workExperienceModel: WorkExperienceModel)


}

