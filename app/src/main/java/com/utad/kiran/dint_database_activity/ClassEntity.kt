package com.utad.kiran.dint_database_activity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName= "Classes")
class ClassEntity(
        @PrimaryKey(autoGenerate = false)
        var document: String,
        var name: String,
        var teacher: String,
        var year: Int?,
        var email: String?,
        var schedule: String?
)