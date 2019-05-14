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
import com.example.mecawash.adapters.RequestAdapter
import com.example.mecawash.models.Local
import com.example.mecawash.models.Request
import com.example.mecawash.network.NewsApi
import com.example.mecawash.network.RequestResponse
import kotlinx.android.synthetic.main.fragment_request.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class RequestFragment : Fragment() {

    var requests =  ArrayList<Request>()
    lateinit var requestRecyclerView: RecyclerView
    lateinit var requestAdapter: RequestAdapter
    lateinit var requestLayoutManager: RecyclerView.LayoutManager

    val TOKEN: String = "Token"
    private val STRING_PREFERENCE = "Session"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_request,container,false)

        //var intent = activity!!.intent ?: return
        var local = Local.from(activity?.intent!!.extras)

        requestRecyclerView = view.requestRecyclerView
        requests = ArrayList()
        requestAdapter = RequestAdapter(requests, view.context)
        requestLayoutManager = GridLayoutManager(view.context,1) as RecyclerView.LayoutManager
        requestRecyclerView.adapter = requestAdapter
        requestRecyclerView.layoutManager = requestLayoutManager


        val result = this.activity!!.getSharedPreferences(STRING_PREFERENCE, AppCompatActivity.MODE_PRIVATE)
        var token = "Bearer " + result.getString(TOKEN, "")

        var url: String = NewsApi.getRequests(local.LocalId)

        NewsApi.requestRequest(token,url,
            {response -> handleResponse(response)},
            {error -> handleError(error)})

        // Inflate the layout for this fragment
        return view
    }

    private fun handleResponse(response: RequestResponse?) {
        val error = response!!.Error
        if (error.equals(true)) {
            Log.d("MecaWash", response.Message)
            return
        }

        requests = response.Data!!
        requestAdapter.requests = requests
        requestAdapter.notifyDataSetChanged()
    }

    private fun handleError(anError: ANError?) {
        Log.d("MecaWash", anError!!.message)
    }


}
