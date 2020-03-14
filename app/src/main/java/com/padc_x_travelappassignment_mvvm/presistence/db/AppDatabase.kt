package com.padc_x_travelappassignment_mvvm.presistence.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.padc_x_travelappassignment_mvvm.data.vos.CountryVo
import com.padc_x_travelappassignment_mvvm.presistence.daos.CountryDao
import com.padc_x_travelappassignment_mvvm.presistence.typeconverters.PhotoTypeConverter
import com.padc_x_travelappassignment_mvvm.presistence.typeconverters.TourTypeConverter

@Database(entities = [CountryVo::class],version = 1,exportSchema = false)
@TypeConverters(TourTypeConverter::class, PhotoTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    companion object{
        val DB_NAME="COUNTRY.DB"
        var dbInstance : AppDatabase? =null

        fun getNewDbInstance(context: Context) : AppDatabase{
            when(dbInstance){
                null -> {
                    dbInstance=Room.databaseBuilder(context,AppDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            val i= dbInstance
            return i!!
        }

    }
    abstract fun dao() : CountryDao
}