package com.padc_x_travelappassignment_mvvm.presistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padc_x_travelappassignment_mvvm.data.vos.CountryVo

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllTourData(list: MutableList<CountryVo>): LongArray

    @Query("select * from country where name = :name ")
    fun getToursDatabyName(name: String): LiveData<CountryVo>

    @Query("select * from country")
    fun getAllData(): LiveData<List<CountryVo>>
}