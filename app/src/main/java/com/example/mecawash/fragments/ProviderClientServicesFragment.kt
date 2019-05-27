package com.example.mecawash.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.error.ANError

import com.example.mecawash.R
import com.example.mecawash.adapters.ServiceAdapter
import com.example.mecawash.models.Service
import com.example.mecawash.network.NewsApi
import com.example.mecawash.network.ServiceResponse
import kotlinx.android.synthetic.main.fragment_provider_client_services.*
import kotlinx.android.synthetic.main.fragment_request.view.*


val TOKENCLIENT: String = "Token"
private val STRING_PREFERENCE = "Session"

class ProviderClientServicesFragment : Fragment() {

    var services =  ArrayList<Service>()
    lateinit var serviceAdapter: ServiceAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_provider_client_services, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        serviceAdapter = ServiceAdapter(services, view.context)
        serviceClientRecyclerView.apply {
            adapter = serviceAdapter
            layoutManager = LinearLayoutManager(this.context) as RecyclerView.LayoutManager?
        }

        val result = this.activity!!.getSharedPreferences(STRING_PREFERENCE, AppCompatActivity.MODE_PRIVATE)
        var token = "Bearer " + result.getString(TOKENCLIENT, "")

        var url: String = NewsApi.getServices(1)

        NewsApi.requestServices(token,url,
            {response -> handleResponse(response)},
            {error -> handleError(error)})
    }

    private fun handleResponse(response: ServiceResponse?) {
        val error = response!!.Error
        if (error.equals(true)) {
            Log.d("MecaWash", response.Message)
            return
        }

        services = response.Data!!
        serviceAdapter.services = services
        serviceAdapter.notifyDataSetChanged()
    }

    private fun handleError(anError: ANError?) {
        Log.d("MecaWash Error", anError!!.message)
    }

}
