package com.example.anymindtest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* model class for store education data in room database
* */
@Entity
data class EducationModel(val className:String,val passingYear:String,val percentage:String){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
