package com.example.mecawash.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.mecawash.R
import com.example.mecawash.activities.HomeProviderActivity
import com.example.mecawash.activities.RequestStatusAcceptActivity
import com.example.mecawash.activities.RequestStatusActiveActivity
import com.example.mecawash.activities.RequestStatusFinishActivity
import com.example.mecawash.models.Car
import com.example.mecawash.models.Customer
import com.example.mecawash.models.Local
import com.example.mecawash.models.Request
import com.example.mecawash.network.CarResponse
import com.example.mecawash.network.CustomerResponse
import com.example.mecawash.network.LocalsResponse
import com.example.mecawash.network.NewsApi
import kotlinx.android.synthetic.main.item_local.view.*
import kotlinx.android.synthetic.main.item_request.view.*
import java.util.*

class RequestAdapter(var requests: ArrayList<Request>, val context: Context) : RecyclerView.Adapter<RequestAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_request, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return requests.size
    }

    override fun onBindViewHolder(holder: RequestAdapter.ViewHolder, position: Int) {
        holder.updateFrom(requests[position])
    }

    class ViewHolder(requestView: View) : RecyclerView.ViewHolder(requestView) {

        val carTV = requestView.carTextView
        val customerTV = requestView.customerTextView
        val dateTV= requestView.dateTextView
        val statusTV = requestView.estadoTextView
        val requestL = requestView.requestLayout

        fun updateFrom(request: Request) {

            dateTV.text = request.Fecha
            carTV.text = request.NameCar + " - " + request.Placa
            customerTV.text = request.NameCustomer
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

            requestL.setOnClickListener { requestView ->
                val context = requestView.context
                if(request.Status == "ACT"){
                    context.startActivity(Intent(context, RequestStatusActiveActivity::class.java).putExtras(request.toBundle()))
                }else if(request.Status == "CAN"){
                    context.startActivity(Intent(context, RequestStatusFinishActivity::class.java).putExtras(request.toBundle()))
                }else if(request.Status == "FIN"){
                    context.startActivity(Intent(context, RequestStatusFinishActivity::class.java).putExtras(request.toBundle()))
                }else if(request.Status == "ACE"){
                    context.startActivity(Intent(context, RequestStatusAcceptActivity::class.java).putExtras(request.toBundle()))
                }
            }
        }
    }
}