package com.example.busfleets.trainSchedule

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passengers")
data class BusPassenger (
    @PrimaryKey @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "name")val name: String,
    @ColumnInfo(name = "status")val status: String,
    @ColumnInfo(name = "placeNum")val placeNum: Int,
    @ColumnInfo(name = "placeType")val placeType: String,
    @ColumnInfo(name = "placeIndex")val placeIndex: String
)


