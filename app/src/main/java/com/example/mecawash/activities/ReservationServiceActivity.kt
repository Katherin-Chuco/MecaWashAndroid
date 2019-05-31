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
import com.example.mecawash.network.CarsResponse
import com.example.mecawash.network.NewsApi
import com.example.mecawash.network.PostResponse

import kotlinx.android.synthetic.main.activity_reservation_service.*
import kotlinx.android.synthetic.main.content_reservation_service.*
import java.util.*

class ReservationServiceActivity : AppCompatActivity() {

    private var mDisplayDate: TextView? = null
    private var mDisplayTime: TextView? = null
    private var carSpinner: Spinner? = null
    private var cars : List<Car>? = null
    private var carSelected: Int = -1
    private var detailReserve: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_service)

        mDisplayDate = findViewById(R.id.dateButton) as TextView

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        mDisplayDate!!.setText(""+ month + "-" + day + "-" + year)

        mDisplayDate!!.setOnClickListener {
            val monthDatePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay ->
                    mDisplayDate!!.setText(""+ mMonth + "-" + mDay + "-" + mYear)
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


        detailReserve = findViewById(R.id.detailText) as TextView

        val result = getSharedPreferences("Session", AppCompatActivity.MODE_PRIVATE)
        val token = "Bearer " + result.getString("Token", "")
        val customerId = result.getInt("CustomerId", 3)

        val urlCars: String = NewsApi.getCustomerCars(customerId)

        NewsApi.requestCars(token, urlCars,
            {response -> handleCarsResponse(response)},
            {error -> handleError(error)})



        sendButton.setOnClickListener {

            val url: String = NewsApi.sendReservation(customerId)
            val serviceIntento = intent.extras

            val localId: Int = serviceIntento.getInt("LocalId")
            val serviceId: Int = serviceIntento.getInt("ServiceId")
            val schedule: String = mDisplayTime!!.text.toString()
            val detail: String = detailReserve!!.text.toString()
            val status: String = "ACT"
            val carId: Int = cars!![carSelected].CarId
            val cotización: String = "SI"
            val fecha: String = mDisplayDate!!.text.toString()
            val messageProvider: String = ""

            NewsApi.requestReservation(token, url, customerId, localId, serviceId, schedule, detail, status, carId, cotización, fecha, messageProvider,
                {response -> handleReservationResponse(response)},
                {error -> handleError(error)})
        }

        cancelButton.setOnClickListener {view ->
            val context = view.context
            context.startActivity(Intent(context, ServiceClientActivity::class.java).putExtras(intent?.extras))
        }

    }

    private fun handleCarsResponse(response: CarsResponse?) {
        val error = response!!.Error
        if (error.equals(true)) {
            Log.d("MecaWash", response.Message)
            return
        }

        cars = response.Data
        spinnerSelection(response.Data!![0].Brand, response.Data!![1].Brand)
    }

    private fun handleReservationResponse(response: PostResponse?) {
        val error = response!!.Error
        if (error.equals(true)) {
            Log.d("MecaWash", response.Message)
            return
        }

        Log.d("ReservationSuccess",response.Message )

        val intento = Intent(this, HomeCustomerActivity::class.java)
        startActivity(intento)
    }

    private fun handleError(anError: ANError?) {
        Log.d("ReservationError", anError!!.message)
    }


    fun spinnerSelection(carOne: String?, carTwo: String?) {
        carSpinner = findViewById(R.id.carSelect) as Spinner
        //val option = arrayOf("Toyota - Coroya", "Kio - Rio")

        val option = arrayOf(carOne, carTwo)

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
