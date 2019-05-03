package com.example.mecawash.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.mecawash.R
import com.example.mecawash.models.Car
import com.example.mecawash.network.CarResponse

import kotlinx.android.synthetic.main.activity_reservation_service.*
import java.util.*

class ReservationServiceActivity : AppCompatActivity() {

    private var mDisplayDate: TextView? = null
    private var mDisplayTime: TextView? = null
    private var carSpinner: Spinner? = null
    private var cars : List<Car>? = null
    private var carSelected: Int = -1
    private val mDateSetListener: DatePickerDialog.OnDateSetListener? = null
    val dateB: Bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_service)

        mDisplayDate = findViewById(R.id.dateButton) as TextView

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        mDisplayDate!!.setText(""+ day + "/" + month + "/" + year)

        mDisplayDate!!.setOnClickListener {
            val monthDatePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay ->
                    mDisplayDate!!.setText(""+ mDay + "/" + mMonth + "/" + mYear)
                },
                year,
                month,
                day)

            monthDatePickerDialog.show()
        }

        mDisplayTime = findViewById(R.id.hourButton) as TextView

        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minutes = c.get(Calendar.MINUTE)
        mDisplayTime!!.setText(""+hour+":"+minutes)

        mDisplayTime!!.setOnClickListener {
            val timePicker = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener{ view, hourOfDay, minuteOfDay ->
                    mDisplayTime!!.setText(""+ hourOfDay + ":" + minuteOfDay)
                },
                hour,
                minutes,
                false
            )
            timePicker.show()
        }

        //getCars()
        spinnerSelection()
    }


    fun cancel(view: View) {
        val intent = Intent(this, ServiceClientActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun send(view: View) {

    }

    fun getCars() {

    }

    fun senReservation(view: View){

        //AndroidNetworking.post("http://64.202.186.215/APIMekaWash/wamekawash/v6/customers/3/reservations")
        //    .addBodyParameter("firstname", "Amit")
        //    .addBodyParameter("lastname", "Shekhar")
        //    .setPriority(Priority.MEDIUM)
        //    .build()
        //    .getAsJSONArray(
        //
        //    )
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun spinnerSelection() {
        carSpinner = findViewById(R.id.carSelect) as Spinner
        //val option = Array<String>(10) {"it = $it"}
        val option = arrayOf("Toyota - Coroya", "Kio - Rio")

        //for (car in cars!!) {
        //    Log.d("Placa", "hola"+car.id )
        //    option.set(car.id, ""+car.id)
        //}

        carSpinner!!.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, option)

        carSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                carSelected = id.toInt()
            }
        }

    }

}
