package com.example.busfleets

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.busfleets.trainSchedule.BusPassenger
import kotlinx.android.synthetic.main.passenger_item_layout.view.*

class PassengerListAdapter(
    private val busPassengerList: List<BusPassenger> = ArrayList(),
    private val onPassengerClick:(BusPassenger) -> Unit
) :RecyclerView.Adapter<PassengerListAdapter.HintViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HintViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.passenger_item_layout, parent, false)
        return HintViewHolder(view)
    }

    override fun getItemCount(): Int {
        return busPassengerList.count()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: HintViewHolder, position: Int) {
        holder.bindHint(busPassengerList[position])
    }


    inner class HintViewHolder(
        private val view: View
    ):RecyclerView.ViewHolder(view){
        @SuppressLint("SetTextI18n")
        fun bindHint(busPassenger: BusPassenger){
            view.passenger_name.text = busPassenger.name
            view.place_num.text = "${busPassenger.placeNum.toString()} ${busPassenger.placeIndex}"
            view.place_type.text = busPassenger.placeType
            val linearLayoutBg = view.status_bg
            when (busPassenger.status) {
                "OFFLINE" -> {
                    view.status.text = "OFFLINE"
                    linearLayoutBg.setBackgroundResource(R.drawable.status_offline_bg)
                }
                "ONLINE" -> {
                    view.status.text = "ONLINE"
                    linearLayoutBg.setBackgroundResource(R.drawable.status_bg)
                }
                else -> {
                    view.status.text = "no type"
                    linearLayoutBg.setBackgroundResource(R.drawable.status_bg)
                }
            }
            view.setOnClickListener{
                onPassengerClick(busPassenger)
            }

        }
    }

}
