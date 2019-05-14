package com.example.mecawash.controllers.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.androidnetworking.error.ANError
import com.example.mecawash.R
import com.example.mecawash.models.Request
import com.example.mecawash.network.NewsApi
import com.example.mecawash.network.PostResponse
import kotlinx.android.synthetic.main.activity_request_status_active.*

class RequestStatusActiveActivity : AppCompatActivity() {

    val TOKEN: String = "Token"
    private val STRING_PREFERENCE = "Session"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_status_active)

        val intent = intent ?: return
        var request = Request.from(intent!!.extras)

        val autoDetailTV: TextView = findViewById(R.id.carDetailTextView) as TextView
        autoDetailTV.text = request.NameCar + " - " + request.Placa
        val customerTV: TextView = findViewById(R.id.customerDetailTextView) as TextView
        customerTV.text = request.NameCustomer
        val detailTV: TextView = findViewById(R.id.writeDetailTextView) as TextView
        detailTV.text = request.Detail
        val fechaTV: TextView = findViewById(R.id.fechaTextView) as TextView
        fechaTV.text = request.Fecha
        val horaTV: TextView = findViewById(R.id.HoraTextView) as TextView
        horaTV.text = request.Schedule
        val cotizaciónTV: TextView = findViewById(R.id.DomicilioTextView) as TextView
        cotizaciónTV.text = request.Cotizacion
        val statusTV: TextView = findViewById(R.id.StatusTextView) as TextView
        if(request.Status == "ACT"){
            statusTV.text = "ACTIVO"
            statusTV.setTextColor(Color.parseColor("#EDB000"))
        }else if(request.Status == "CAN"){
            statusTV.text = "CANCELADO"
            statusTV.setTextColor(Color.parseColor("#2715E9"))
        }else if(request.Status == "FIN"){
            statusTV.text = "FINALIZADO"
            statusTV.setTextColor(Color.parseColor("#3A75FD"))
        }else if(request.Status == "ACE"){
            statusTV.text = "ACEPTADO"
            statusTV.setTextColor(Color.parseColor("#50B986"))
        }else if(request.Status == "INA"){
            statusTV.text = "INACTIVO"
            statusTV.setTextColor(Color.parseColor("#6058DB"))
        }
        val result = getSharedPreferences(STRING_PREFERENCE, MODE_PRIVATE)
        var token = "Bearer " + result.getString(TOKEN, "")
        val url: String = NewsApi.getChangeStatus(request.LocalId)


        sendAcceptButton.setOnClickListener {
            var messageProviderET = messageEditText.text.toString()

            NewsApi.requestChangeStatus(token, url, request.ReservationId.toString(),"ACE",messageProviderET,
                {response -> handleResponse(response)},
                {error -> handleError(error)})
        }

        sendDenyButton.setOnClickListener {
            var messageProviderET = messageEditText.text.toString()

            NewsApi.requestChangeStatus(token, url, request.ReservationId.toString(),"CAN",messageProviderET,
                {response -> handleResponse(response)},
                {error -> handleError(error)})
        }


    }

    private fun handleResponse(response: PostResponse?) {
        val error = response!!.Error
        if (error.equals(true)) {
            Log.d("MecaWash", response.Message)
            return
        }

        val intento = Intent(this, LocalActivity::class.java)
        startActivity(intento)
    }

    private fun handleError(anError: ANError?) {
        Log.d("MecaWash", anError!!.message)
    }
}
