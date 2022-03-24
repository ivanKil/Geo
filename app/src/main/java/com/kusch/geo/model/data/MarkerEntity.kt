package com.kusch.geo.model.data

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "markers")
data class MarkerEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val annotation: String?,
) : Parcelable