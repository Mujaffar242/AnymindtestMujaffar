package com.example.anymindtest.database
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.anymindtest.model.EducationModel
import com.example.anymindtest.model.PersonalInfoModel
import com.example.anymindtest.model.WorkExperienceModel

/*
* room query for communicate with sqlite database
* */
@Dao
interface EducationDAO {

    @Insert()
    fun insertSinglevalue(educationModel: EducationModel)

    @Query("SELECT * FROM educationmodel WHERE id = :id")
    fun getSingleValue(id:String):EducationModel


    @Query("select * from educationmodel")
    fun getAllEducationList():LiveData<List<EducationModel>>

    /*
   *for update database model single row
   * use for update task complete or not
    */
    @Update
    fun update(educationModel: EducationModel)


}

