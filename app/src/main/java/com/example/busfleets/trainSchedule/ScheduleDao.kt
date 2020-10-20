package com.example.busfleets.trainSchedule


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.selects.select

@Dao
interface ScheduleDao {

    @Insert
    fun insertSchedule(schedule: Schedule)

    @Query("Select * FROM schedules")
    fun getSchedule(): List<Schedule>

    @Delete
    fun deleteItem(schedule: Schedule)

}