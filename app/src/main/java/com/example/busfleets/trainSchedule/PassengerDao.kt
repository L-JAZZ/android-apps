package com.example.busfleets.trainSchedule


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PassengerDao {

    @Insert
    fun insertPassenger(passenger: BusPassenger)

    @Query("Select * FROM passengers")
    fun getPassenger(): List<BusPassenger>

    @Delete
    fun deleteItem(passenger: BusPassenger)

}