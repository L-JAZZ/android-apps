package com.example.busfleets


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.busfleets.trainSchedule.ApplicationDatabase
import com.example.busfleets.trainSchedule.BusPassenger
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_passengers.*


class PassengersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passengers)
        (this as AppCompatActivity).supportActionBar?.title = "Пассажиры"
        (this as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadRecyclerView()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



    private fun loadRecyclerView(){
        busy_places.layoutManager = LinearLayoutManager(this)
        free_places.layoutManager = LinearLayoutManager(this)
        var placeFreeList = mutableListOf(
            BusPassenger(name = "no name", status = "no type", placeNum = 3, placeType = "нижний", placeIndex = "A"),
            BusPassenger(name = "no name", status = "no type", placeNum = 3, placeType = "верхний", placeIndex = "B"),
            BusPassenger(name = "no name", status = "no type", placeNum = 4, placeType = "нижний", placeIndex = "A"),
            BusPassenger(name = "no name", status = "no type", placeNum = 4, placeType = "верхний", placeIndex = "B"),
            BusPassenger(name = "no name", status = "no type", placeNum = 5, placeType = "нижний", placeIndex = "A"),
            BusPassenger(name = "no name", status = "no type", placeNum = 5, placeType = "верхний", placeIndex = "B"),
            BusPassenger(name = "no name", status = "no type", placeNum = 6, placeType = "нижний", placeIndex = "A"),
            BusPassenger(name = "no name", status = "no type", placeNum = 6, placeType = "верхний", placeIndex = "B")
        )
        free_places.adapter = PassengerListAdapter(busPassengerList = placeFreeList, onPassengerClick = {

        })
        AsyncTask.execute{
            val passengers = ApplicationDatabase.getInstance(applicationContext)
                ?.getPassengerDao()?.getPassenger()

            runOnUiThread {
                busy_places.adapter = passengers?.let {
                    PassengerListAdapter(busPassengerList = it,
                        onPassengerClick = { pass->
                            val bottomSheetDialog =
                                BottomSheetDialog(this, R.style.BottomSheetDialog)
                            val bottomSheetView = LayoutInflater.from(applicationContext)
                                .inflate(
                                    R.layout.layout_popup_window,
                                    findViewById(R.id.bottom_sheet_window)
                                )
                            val cancelBtn =
                                bottomSheetView.findViewById<Button>(R.id.cancel_operation)
                            fillWindow(bottomSheetView, pass)
                            cancelBtn.setOnClickListener {
                                val dialogBuilder = AlertDialog.Builder(this)
                                dialogBuilder.setMessage("Вы хотите отменить поукпку билетв?")
                                dialogBuilder.setPositiveButton("да",
                                    DialogInterface.OnClickListener { dialog, whichButton ->
                                        deletePassenger(pass)
                                        toastMessage()
                                        bottomSheetDialog.dismiss()
                                        val intent = intent
                                        finish()
                                        startActivity(intent)
                                    })
                                dialogBuilder.setNegativeButton("нет",
                                    DialogInterface.OnClickListener { dialog, whichButton ->
                                        dialog.cancel()
                                        bottomSheetDialog.dismiss()
                                    })
                                dialogBuilder.create().show()

                            }
                            bottomSheetDialog.setContentView(bottomSheetView)
                            bottomSheetDialog.show()
                        })
                }
            }
        }
    }
    private fun deletePassenger(passenger: BusPassenger) {
        AsyncTask.execute {
            ApplicationDatabase.getInstance(applicationContext)!!
                .getPassengerDao()
                .deleteItem(passenger)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fillWindow(view: View, passenger: BusPassenger){
        val userName = view.findViewById<EditText>(R.id.user_name)
        val userPhone = view.findViewById<EditText>(R.id.user_phone)
        val userPlace = view.findViewById<EditText>(R.id.user_place)
        val userPrice = view.findViewById<EditText>(R.id.user_price)

        val placeText = "${passenger.placeNum} ${passenger.placeIndex.toString()} ${passenger.placeType}"

        userName.setText(passenger.name)
        userPhone.setText("87079060297")
        userPlace.setText(placeText)
        userPrice.setText("3000")

    }

    @SuppressLint("ResourceAsColor")
    private fun toastMessage(){
        val toast: Toast = Toast.makeText(this,getString(R.string.cancel_label), Toast.LENGTH_LONG)
        val view = toast.view
        view!!.setBackgroundResource(R.drawable.add_btn)
        toast.show()
    }



}