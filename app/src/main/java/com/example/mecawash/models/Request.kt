package com.example.mecawash.models

import android.os.Bundle
import java.sql.Date
import java.sql.Time

data class Request(val ReservationId:Int,
                   val CustomerId: Int,
                   val NameCustomer:String?,
                   val LocalId: Int,
                   val ServiceId: Int,
                   val Schedule: String?,
                   val Detail: String?,
                   val Status: String?,
                   val CarId: Int,
                   val NameCar: String?,
                   val Placa:String?,
                   val Fecha: String?,
                   val Cotizacion: String?,
                   val MessageProvider: String?) {

    companion object {
        fun from(bundle: Bundle): Request {
            return Request(
                bundle.getInt("ReservationId")!!,
                bundle.getInt("CustomerId")!!,
                bundle.getString("NameCustomer")!!,
                bundle.getInt("LocalId")!!,
                bundle.getInt("ServiceId")!!,
                bundle.getString("Schedule")!!,
                bundle.getString("Detail")!!,
                bundle.getString("Status")!!,
                bundle.getInt("CarId")!!,
                bundle.getString("NameCar")!!,
                bundle.getString("Placa")!!,
                bundle.getString("Fecha"),
                bundle.getString("Cotizacion")!!,
                bundle.getString("MessageProvider"))
        }
    }

    fun toBundle() : Bundle {
        val bundle = Bundle()
        //bundle.putBundle("source", source.toBundle())

        bundle.putInt("ReservationId",ReservationId)
        bundle.putInt("CustomerId",CustomerId)
        bundle.putString("NameCustomer",NameCustomer)
        bundle.putInt("LocalId",LocalId)
        bundle.putInt("ServiceId",ServiceId)
        bundle.putString("Schedule",Schedule)
        bundle.putString("Detail",Detail)
        bundle.putString("Status",Status)
        bundle.putInt("CarId",CarId)
        bundle.putString("NameCar",NameCar)
        bundle.putString("Placa",Placa)
        bundle.putString("Fecha",Fecha)
        bundle.putString("Cotizacion",Cotizacion)
        bundle.putString("MessageProvider",MessageProvider)
        return bundle
    }
}