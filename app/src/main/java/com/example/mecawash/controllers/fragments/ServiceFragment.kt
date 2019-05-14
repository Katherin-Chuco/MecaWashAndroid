package com.example.mecawash.controllers.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.error.ANError

import com.example.mecawash.R
import com.example.mecawash.adapters.ServiceAdapter
import com.example.mecawash.models.Local
import com.example.mecawash.models.Service
import com.example.mecawash.network.NewsApi
import com.example.mecawash.network.ServiceResponse
import kotlinx.android.synthetic.main.fragment_request.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

val TOKEN: String = "Token"
private val STRING_PREFERENCE = "Session"

class ServiceFragment : Fragment() {

    var services =  ArrayList<Service>()
    lateinit var serviceRecyclerView: RecyclerView
    lateinit var serviceAdapter: ServiceAdapter
    lateinit var serviceLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_request,container,false)

        var local = Local.from(activity?.intent!!.extras)

        serviceRecyclerView = view.requestRecyclerView
        services = ArrayList()
        serviceAdapter = ServiceAdapter(services, view.context)
        serviceLayoutManager = GridLayoutManager(view.context,1) as RecyclerView.LayoutManager
        serviceRecyclerView.adapter = serviceAdapter
        serviceRecyclerView.layoutManager = serviceLayoutManager

        val result = this.activity!!.getSharedPreferences(STRING_PREFERENCE, AppCompatActivity.MODE_PRIVATE)
        var token = "Bearer " + result.getString(TOKEN, "")

        var url: String = NewsApi.getServices(local.LocalId)

        NewsApi.requestServices(token,url,
            {response -> handleResponse(response)},
            {error -> handleError(error)})

        return view
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
        Log.d("MecaWash", anError!!.message)
    }

}
