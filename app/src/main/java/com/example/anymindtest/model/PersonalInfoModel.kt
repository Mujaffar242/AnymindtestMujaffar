package com.example.anymindtest.model

import android.provider.ContactsContract
import androidx.room.Entity
import androidx.room.PrimaryKey


/*
* model class for store personal info data in room database
* */
@Entity
data class PersonalInfoModel(
    val imagePath:String,
    val email: String, val mobile: String, val address: String, val careerObjective:String,
    val yearOfExperience:String

) {
    @PrimaryKey
    var id: Int = 1
}