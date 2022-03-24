package com.kusch.geo.di

import android.app.Application
import androidx.room.Room
import com.kusch.geo.model.AppDatabase
import com.kusch.geo.model.dao.MarkerDao
import com.kusch.geo.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val applicationModule = module {

    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "markers.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    fun provideDao(database: AppDatabase): MarkerDao {
        return database.markerDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }

    viewModel { HomeViewModel(get()) }
}
