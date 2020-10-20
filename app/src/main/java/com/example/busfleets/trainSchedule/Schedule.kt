package com.example.busfleets.trainSchedule

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedules")
data class Schedule (
    @PrimaryKey @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "fromCity") val fromCity: String,
    @ColumnInfo(name = "toCity") val toCity: String,
    @ColumnInfo(name = "placeNum") val placeNum: Int,
    @ColumnInfo(name = "busNum") val busNum: String,
    @ColumnInfo(name = "arrivalDate") val arrivalDate: String,
    @ColumnInfo(name = "arrivalTime") val arrivalTime: String,
    @ColumnInfo(name = "departureDate") val departureDate: String,
    @ColumnInfo(name = "departureTime") val departureTime: String,
    @ColumnInfo(name = "image") val image: Int,
    @ColumnInfo(name = "model") val model: String
)