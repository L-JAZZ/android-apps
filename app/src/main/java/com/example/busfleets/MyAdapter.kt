package com.example.busfleets

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.busfleets.trainSchedule.ApplicationDatabase
import com.example.busfleets.trainSchedule.Schedule
import kotlinx.android.synthetic.main.schedule_item_layout.view.*

class MyAdapter(
    private val scheduleList: List<Schedule> = listOf(),
//    private val context: Context,
    private val onScheduleClick:(Schedule) -> Unit
) :RecyclerView.Adapter<MyAdapter.HintViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HintViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.schedule_item_layout, parent, false)
        return HintViewHolder(view)
    }

    override fun getItemCount(): Int {
        return scheduleList.count()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: HintViewHolder, position: Int) {
        holder.bindHint(scheduleList[position])
    }


    inner class HintViewHolder(
        private val view: View
    ):RecyclerView.ViewHolder(view){
        @RequiresApi(Build.VERSION_CODES.O)
        @SuppressLint("SetTextI18n")
        fun bindHint(schedule: Schedule){
            view.bus_model.text = schedule.model
            view.bus_nomer.text = schedule.busNum
            view.bus_place_num.text = "${schedule.placeNum.toString()} мест"
            view.from_to_city.text = "${schedule.fromCity} - ${schedule.toCity}"
            view.arrival_time.text = "Время - ${schedule.arrivalTime}"
            view.departure_time.text = "Время - ${schedule.departureTime}"
            view.bus_image.setImageResource(schedule.image)
            view.departure_date.text = "Дата - ${schedule.departureDate}"
            view.arrival_date.text = "Дата - ${schedule.arrivalDate}"

            view.setOnClickListener{
                onScheduleClick(schedule)
            }
        }
    }
}
