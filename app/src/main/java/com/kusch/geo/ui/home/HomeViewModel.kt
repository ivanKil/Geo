package com.kusch.geo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kusch.geo.model.dao.MarkerDao
import com.kusch.geo.model.data.MarkerEntity
import kotlinx.coroutines.launch

class HomeViewModel(private val markerDao: MarkerDao) : ViewModel() {

    val ldMarkers: MutableLiveData<List<MarkerEntity>> = MutableLiveData()
    val ldAddMarker: MutableLiveData<MarkerEntity> = MutableLiveData()

    fun requestMarkers() {
        viewModelScope.launch {
            ldMarkers.value = markerDao.findAll()
        }
    }

    fun addMarker(markerEntity: MarkerEntity) {
        viewModelScope.launch {
            markerDao.add(markerEntity)
            ldAddMarker.value = markerEntity
        }
    }
}