package com.example.busfleets

import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.Menu
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.busfleets.trainSchedule.ApplicationDatabase
import com.example.busfleets.trainSchedule.BusPassenger
import com.example.busfleets.trainSchedule.Schedule
import com.google.android.material.navigation.NavigationView
import java.time.LocalDate


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
            insertSchedule()
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_schedule), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun insertSchedule(){
        val depDate1 = LocalDate.now()
        val depDate2 = LocalDate.now()
        val arrDate1 = LocalDate.now().plusDays(1)
        val arrDate2 = LocalDate.now().plusDays(1)
        val arrDate3 = LocalDate.now().plusDays(2)
        val arrDate4 = LocalDate.now().plusDays(2)

        AsyncTask.execute{
            ApplicationDatabase.getInstance(applicationContext)!!.getScheduleDao().insertSchedule(Schedule(fromCity = "Асыката", toCity = "Алматы", departureTime = "18:39", arrivalTime = "6:10", departureDate = depDate1.toString(), arrivalDate = arrDate1.toString(), busNum = "KZ 888 KN 02", placeNum = 32, model = "Yokohama", image = R.drawable.busf))
            ApplicationDatabase.getInstance(applicationContext)!!.getScheduleDao().insertSchedule(Schedule(fromCity = "Астана", toCity = "Алматы", departureTime = "19:20", arrivalTime = "4:36", departureDate = depDate2.toString(), arrivalDate = arrDate2.toString() ,busNum = "KZ 998 KN 03", placeNum = 46,  model = "Volvo", image = R.drawable.buss))
            ApplicationDatabase.getInstance(applicationContext)!!.getScheduleDao().insertSchedule(Schedule(fromCity = "Актау", toCity = "Атырау", departureTime = "15:30", arrivalTime = "9:40", departureDate = arrDate1.toString(), arrivalDate = arrDate3.toString(), busNum = "KZ 468 KN 02", placeNum = 48,  model = "Mazda", image = R.drawable.bust))
            ApplicationDatabase.getInstance(applicationContext)!!.getScheduleDao().insertSchedule(Schedule(fromCity = "Кокшетау", toCity = "Жезказган", departureTime = "18:19", arrivalTime = "7:20", departureDate = arrDate2.toString(), arrivalDate = arrDate4.toString(), busNum = "KZ 346 KN 02", placeNum = 52,  model = "Yokohama", image = R.drawable.busfo))
            insertPassengers()
        }
    }

    private fun insertPassengers(){
        AsyncTask.execute{
            ApplicationDatabase.getInstance(applicationContext)!!.getPassengerDao().insertPassenger(
                BusPassenger(name = "Aigerim", status = "OFFLINE", placeNum = 0, placeType = "нижний", placeIndex = "A")
            )
            ApplicationDatabase.getInstance(applicationContext)!!.getPassengerDao().insertPassenger(
                BusPassenger(name = "Arlan", status = "OFFLINE", placeNum = 0, placeType = "верхний", placeIndex = "B")
            )
            ApplicationDatabase.getInstance(applicationContext)!!.getPassengerDao().insertPassenger(
                BusPassenger(name = "ASSEL", status = "ONLINE", placeNum = 1, placeType = "нижний", placeIndex = "A")
            )
            ApplicationDatabase.getInstance(applicationContext)!!.getPassengerDao().insertPassenger(
                BusPassenger(name = "TEMIRLAN", status = "ONLINE", placeNum = 1, placeType = "верхний", placeIndex = "B")
            )
        }
    }


}