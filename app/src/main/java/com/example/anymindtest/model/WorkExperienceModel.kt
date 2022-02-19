package com.example.anymindtest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* model class for store work experience data in room database
* */
@Entity
data class WorkExperienceModel(val company:String,val startDate:String,val endDate:String){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
