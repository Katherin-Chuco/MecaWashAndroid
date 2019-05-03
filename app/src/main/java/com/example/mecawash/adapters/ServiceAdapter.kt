package com.example.mecawash.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mecawash.R
import com.example.mecawash.models.Request
import com.example.mecawash.models.Service
import kotlinx.android.synthetic.main.item_service.view.*
import java.util.ArrayList

class ServiceAdapter (var services: ArrayList<Service>, val context: Context) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ServiceAdapter.ViewHolder {
        return ServiceAdapter.ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_request, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return services.size
    }

    override fun onBindViewHolder(holder: ServiceAdapter.ViewHolder, position: Int) {
        holder.updateFrom(services[position])
    }

    class ViewHolder(serviceView: View) : RecyclerView.ViewHolder(serviceView) {

        val categoryTextView = serviceView.categoryTextView
        val PunctuationRatingBar = serviceView.nameServiceTextView
        val articleLayout = serviceView.costTextView

        fun updateFrom(service: Service) {

            //addressTextView.text = service.Address
            //PunctuationRatingBar.rating = service.Punctuation.toFloat()

            //articleLayout.setOnClickListener { view ->
            //    val context = view.context
            //    context.startActivity(Intent(context, HomeProviderActivity::class.java).putExtras(local.toBundle()))
            //}
        }
    }
}