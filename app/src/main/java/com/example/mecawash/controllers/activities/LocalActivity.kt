package com.example.mecawash.controllers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.error.ANError
import com.example.mecawash.R
import com.example.mecawash.adapters.LocalAdapter
import com.example.mecawash.models.Local
import com.example.mecawash.network.LocalsResponse
import com.example.mecawash.network.NewsApi

class LocalActivity : AppCompatActivity() {

    var locals = ArrayList<Local>()
    lateinit var localsRecyclerView: RecyclerView
    lateinit var localsAdapter: LocalAdapter
    lateinit var localsLayoutManager: RecyclerView.LayoutManager

    //SHARE PREFERENCE
    val TOKEN: String = "Token"
    private val STRING_PREFERENCE = "Session"
    private val NOMBREPROVIDER = "NombreProvider"
    private val PROVIDERID = "ProviderId"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local)

        localsRecyclerView = findViewById(R.id.localsRecyclerView) as RecyclerView
        localsAdapter = LocalAdapter(locals)
        localsLayoutManager = GridLayoutManager(this, 1) as RecyclerView.LayoutManager
        localsRecyclerView.adapter = localsAdapter
        localsRecyclerView.layoutManager = localsLayoutManager

        val result = getSharedPreferences(STRING_PREFERENCE, MODE_PRIVATE)
        var token = "Bearer " + result.getString(TOKEN, "")
        var providerId = result.getInt(PROVIDERID,0);
        var NameProvider = result.getString(NOMBREPROVIDER, "")

        val messageWelcomeTW: TextView = findViewById(R.id.bienvenidaTextView) as TextView
        messageWelcomeTW.text = "Bienvenido ${NameProvider}"


        val url: String = NewsApi.getLocals(providerId)

        NewsApi.requestLocals(token,url,
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
