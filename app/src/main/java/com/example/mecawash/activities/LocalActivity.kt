package com.example.mecawash.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.mecawash.R
import com.example.mecawash.adapters.LocalAdapter
import com.example.mecawash.models.Local
import com.example.mecawash.network.LocalsResponse
import com.example.mecawash.network.NewsApi
import kotlinx.android.synthetic.main.activity_local.*

class LocalActivity : AppCompatActivity() {

    var locals = ArrayList<Local>()
    lateinit var localsRecyclerView: RecyclerView
    lateinit var localsAdapter: LocalAdapter
    lateinit var localsLayoutManager: RecyclerView.LayoutManager

    private val STRING_PREFERENCE = "Session"
    private val ACCOUNT_TOKEN = "userToken"
    private val TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6ImNoZW1hbG9uc285NiIsIm5iZiI6MTU1NjQ2MDI1MywiZXhwIjoxNTU2NDYzODUzLCJpYXQiOjE1NTY0NjAyNTMsImlzcyI6Imh0dHA6Ly82NC4yMDIuMTg2LjIxNS9BUElNZWthV2FzaCIsImF1ZCI6Imh0dHA6Ly82NC4yMDIuMTg2LjIxNS9BUElNZWthV2FzaCJ9.OkXDIjjoB_JvrYaxJLnU1IjOIbwMICMJzOEDEzGcHe4"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local)

        localsRecyclerView = findViewById(R.id.localsRecyclerView) as RecyclerView
        localsAdapter = LocalAdapter(locals)
        localsLayoutManager = GridLayoutManager(this, 1) as RecyclerView.LayoutManager
        localsRecyclerView.adapter = localsAdapter
        localsRecyclerView.layoutManager = localsLayoutManager

        //COLOCARLO EN EL LOGIN
        //var preferences : SharedPreferences = this.getSharedPreferences(STRING_PREFERENCE,MODE_PRIVATE)

        val url: String = NewsApi.getLocal(1)

        NewsApi.requestLocal(TOKEN,url,
            {response -> handleResponse(response)},
            {error -> handleError(error)})

    }

    private fun handleResponse(response: LocalsResponse?) {
        val error = response!!.Error
        if (error.equals(true)) {
            Log.d("MecaWash", response.Message)
            return
        }

        locals = response.Data!!
        Log.d("MecaWash", "Found ${locals.size} locals")
        localsAdapter.locals = locals
        localsAdapter.notifyDataSetChanged()
    }

    private fun handleError(anError: ANError?) {
        Log.d("MecaWash", anError!!.message)
    }


}
