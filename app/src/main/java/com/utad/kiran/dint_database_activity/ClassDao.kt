package com.utad.kiran.dint_database_activity

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface ClassDao{
    @Insert
    fun insert(classes: ClassEntity): Long

    @Insert
    fun insert(aClasses: List<ClassEntity>): LongArray

    @Update
    fun update(aClass: ClassEntity): Int

    @Query("DELETE FROM classes")
    fun deleteAll()


    @Query("SELECT * FROM classes ORDER BY name ASC")
    fun getAllClasses(): LiveData<List<ClassEntity>>

    @Query("SELECT * FROM classes WHERE document = :classDocument LIMIT 1")
    fun getClassesByDocument(classDocument: String): LiveData<ClassEntity>
}
