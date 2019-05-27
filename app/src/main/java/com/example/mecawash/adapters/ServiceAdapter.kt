package com.example.mecawash.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mecawash.R
import com.example.mecawash.activities.ServiceClientActivity
import com.example.mecawash.models.Request
import com.example.mecawash.models.Service
import kotlinx.android.synthetic.main.item_service.view.*
import java.util.ArrayList

class ServiceAdapter (var services: ArrayList<Service>, val context: Context) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ServiceAdapter.ViewHolder {
        return ServiceAdapter.ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_service, parent, false)
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
        val nameTextView = serviceView.nameServiceTextView
        val costTextView = serviceView.costTextView
        val serviceLayout = serviceView.serviceLayout

        fun updateFrom(service: Service) {

            categoryTextView.text = service.NameCategory
            costTextView.text = service.Cost.toString()
            nameTextView.text = service.Name

            serviceLayout.setOnClickListener { view ->
                val context = view.context
                context.startActivity(Intent(context, ServiceClientActivity::class.java).putExtras(service.toBundle()))
                //context.startActivity(Intent(context, ServiceClientActivity::class.java).putExtra("service", service as Serializable))
            }
        }
    }
}