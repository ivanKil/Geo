package com.kusch.geo.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kusch.geo.model.data.MarkerEntity

@Dao
interface MarkerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(markerEntity: MarkerEntity)

    @Query("SELECT * FROM markers")
    fun findAll(): List<MarkerEntity>

    @Query("DELETE FROM markers")
    fun deleteAllData()

}