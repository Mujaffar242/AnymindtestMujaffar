package com.example.anymindtest.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ProjectModel(val projectName:String, val teamSize:String, val projectSummery:String,val technologyUsed:String,val role:String){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
