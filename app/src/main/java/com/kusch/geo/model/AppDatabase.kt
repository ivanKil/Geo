package com.kusch.geo.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kusch.geo.model.dao.MarkerDao
import com.kusch.geo.model.data.MarkerEntity

@Database(entities = [MarkerEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val markerDao: MarkerDao
}