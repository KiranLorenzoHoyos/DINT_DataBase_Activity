package com.utad.kiran.dint_database_activity

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.os.AsyncTask

@Database(entities = [ClassEntity::class],  version = 1)
abstract class AppDatabase:RoomDatabase(){
    abstract fun ClassDao(): ClassDao
        companion object {
            private val DB_Name = BuildConfig.DATABASE_NAME
            @Volatile
            private var INSTANCE: AppDatabase? = null

            fun getInstance(context: Application): AppDatabase = INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

            fun destroyInstance() {
                INSTANCE
            }

            private fun buildDatabase(context: Application) =
                    Room.databaseBuilder(context, AppDatabase::class.java, DB_Name).fallbackToDestructiveMigration().addCallback(sRoomDatabaseCallback).build()

            private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    PopulateDbAsync(INSTANCE).execute()
                }
            }

            private class PopulateDbAsync internal constructor(db: AppDatabase?) : AsyncTask<String, String, String> (){
                override fun doInBackground(vararg params: String?): String {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            }

            private var classes: List<ClassEntity> = listOf(
                    ClassEntity(document = "0", name = "ADAT", teacher = "Jaime", year = 2018, email = "jaime.latorre@live.u-tad.com", schedule = "Lunes y miercoles de 13h a 15h"),
                    ClassEntity(document = "1", name = "DINT", teacher = "David", year = 2018, email = "david.jardon@live.u-tad.com", schedule = "Martes de 9h a 11h y miercoles de 9h a 15h"),
                    ClassEntity(document = "2", name = "PSPR", teacher = "Pedro", year = 2018, email = "pedro.camacho@live.u-tad.com", schedule = "Martes de 13h a 15h y jueves de 11h a 13h"),
                    ClassEntity(document = "3", name = "ITGS", teacher = "Cristina", year = 2018, email = "cristina.espinosa@live.u-tad.com", schedule = "Jueves de 91h a 11h"),
                    ClassEntity(document = "4", name = "PMDM", teacher = "Carlos", year = 2018, email = "carlos.jimenez@live.u-tad.com", schedule = "Lunes de 9h a 13h")
            )
        }
}