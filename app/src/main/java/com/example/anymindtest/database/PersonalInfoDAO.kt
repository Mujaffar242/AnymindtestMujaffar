package com.example.anymindtest.database
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.anymindtest.model.EducationModel
import com.example.anymindtest.model.PersonalInfoModel

/*
* room query for communicate with sqlite database
* */
@Dao
interface PersonalInfoDAO {

    @Insert()
    fun insertSinglevalue(personalInfoModel: PersonalInfoModel)

    @Query("SELECT * FROM PersonalInfoModel WHERE id = :id")
    fun getSingleValue(id:Int):PersonalInfoModel

    @Query("select * from PersonalInfoModel")
    fun getPersonalInfo():LiveData<List<PersonalInfoModel>>

    /*
   *for update database model single row
   * use for update task complete or not
    */
    @Update
    fun update(personalInfoModel: PersonalInfoModel)


}

