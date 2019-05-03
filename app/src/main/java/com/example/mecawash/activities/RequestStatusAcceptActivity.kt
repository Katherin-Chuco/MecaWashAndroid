package com.example.mecawash.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.androidnetworking.error.ANError
import com.example.mecawash.R
import com.example.mecawash.models.Request
import com.example.mecawash.network.LocalsResponse
import com.example.mecawash.network.NewsApi

class RequestStatusAcceptActivity : AppCompatActivity() {

    val TOKEN: String = "Token"
    private val STRING_PREFERENCE = "Session"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_status_accept)
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
        if (request.Status == "ACT") {
            statusTV.text = "ACTIVO"
            statusTV.setTextColor(Color.parseColor("#EDB000"))
        } else if (request.Status == "CAN") {
            statusTV.text = "CANCELADO"
            statusTV.setTextColor(Color.parseColor("#2715E9"))
        } else if (request.Status == "FIN") {
            statusTV.text = "FINALIZADO"
            statusTV.setTextColor(Color.parseColor("#3A75FD"))
        } else if (request.Status == "ACE") {
            statusTV.text = "ACEPTADO"
            statusTV.setTextColor(Color.parseColor("#50B986"))
        } else if (request.Status == "INA") {
            statusTV.text = "INACTIVO"
            statusTV.setTextColor(Color.parseColor("#6058DB"))
        }
        val messageProviderTV: TextView = findViewById(R.id.messageProviderTextView) as TextView
        messageProviderTV.text = request.MessageProvider
    }
}
