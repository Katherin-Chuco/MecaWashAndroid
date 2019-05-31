package com.example.mecawash.fragments

import android.content.Context
import android.net.Uri
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
import com.example.mecawash.adapters.RequestAdapter
import com.example.mecawash.models.Request
import com.example.mecawash.network.NewsApi
import com.example.mecawash.network.RequestResponse
import kotlinx.android.synthetic.main.fragment_request.view.*
import kotlinx.android.synthetic.main.fragment_request_customer.view.*


class RequestCustomerFragment : Fragment() {

    var requests =  ArrayList<Request>()
    lateinit var requestClientRecyclerView: RecyclerView
    lateinit var requestAdapter: RequestAdapter
    lateinit var requestLayoutManager: RecyclerView.LayoutManager

    val TOKEN: String = "Token"
    private val STRING_PREFERENCE = "Session"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_request_customer, container, false)

        requestClientRecyclerView = view.requestClientRecyclerView
        requests = ArrayList()
        requestAdapter = RequestAdapter(requests, view.context)
        requestLayoutManager = GridLayoutManager(view.context,1) as RecyclerView.LayoutManager
        requestClientRecyclerView.adapter = requestAdapter
        requestClientRecyclerView.layoutManager = requestLayoutManager

        val result = this.activity!!.getSharedPreferences(STRING_PREFERENCE, AppCompatActivity.MODE_PRIVATE)
        var token = "Bearer " + result.getString(TOKEN, "")
        val clientId = result.getInt("CustomerId", 3)

        var url: String = NewsApi.getCustomersRequests(clientId)

        NewsApi.requestRequest(token,url,
            {response -> handleResponse(response)},
            {error -> handleError(error)})

        return view
    }

    private fun handleResponse(response: RequestResponse?) {
        val error = response!!.Error
        if (error.equals(true)) {
            Log.d("MecaWash", response.Message)
            return
        }

        requests = response.Data!!
        Log.d("RequestCliente", requests.toString())
        requestAdapter.requests = requests
        requestAdapter.notifyDataSetChanged()
    }

    private fun handleError(anError: ANError?) {
        Log.d("MecaWash", anError!!.message)
    }


}
