package com.example.busfleets.ui

import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.busfleets.MyAdapter
import com.example.busfleets.PassengersActivity
import com.example.busfleets.R
import com.example.busfleets.trainSchedule.ApplicationDatabase
import com.example.busfleets.trainSchedule.Schedule
import java.time.LocalDate

class ScrollingFragment : Fragment() {

    private lateinit var scheduleViewModel: ScheduleViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scheduleViewModel =
            ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_scrolling, container, false)

        val recToday = root.findViewById<RecyclerView>(R.id.schedule_today_list)
        val recTomorrow = root.findViewById<RecyclerView>(R.id.schedule_tomorrow_list)
        recToday.layoutManager = LinearLayoutManager(activity)
        recTomorrow.layoutManager = LinearLayoutManager(activity)
        AsyncTask.execute {
            val schedule = ApplicationDatabase.getInstance(requireActivity().applicationContext)!!
                .getScheduleDao().getSchedule()

            activity?.runOnUiThread {
                var todayList: List<Schedule> = listOf()
                var tomorrowList: List<Schedule> = listOf()
                for (i in schedule) {
                    val depDate = LocalDate.parse(i.departureDate)
                    val current = LocalDate.now()
                    if (depDate.isEqual(current)) {
                        todayList += i
                    }
                    if (depDate.isAfter(current)){
                        tomorrowList += i
                    }
                }
                recToday.adapter = todayList.let {
                    MyAdapter(scheduleList = it, onScheduleClick = {
                        val intent = Intent(activity, PassengersActivity::class.java)
                        startActivity(intent)
                    })
                }
                recTomorrow.adapter = tomorrowList.let {
                    MyAdapter(scheduleList = it, onScheduleClick = {
                        val intent = Intent(activity, PassengersActivity::class.java)
                        startActivity(intent)
                    })
                }
            }
            }
        val textView: TextView = root.findViewById(R.id.today_label)
        val textViewTomorrow: TextView = root.findViewById(R.id.tomorrow_label)
        scheduleViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
            textViewTomorrow.text = it
        })
        return root
    }


}