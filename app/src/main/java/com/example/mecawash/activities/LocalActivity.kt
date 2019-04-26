package com.example.mecawash.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.error.ANError
import com.example.mecawash.R
import com.example.mecawash.adapters.LocalAdapter
import com.example.mecawash.models.Local
import com.example.mecawash.network.LocalsResponse
import com.example.mecawash.network.NewsApi
import kotlinx.android.synthetic.main.activity_local.*

class LocalActivity : AppCompatActivity() {

    var locals = ArrayList<Local>()
    lateinit var localRecyclerView: RecyclerView
    lateinit var localAdapter: LocalAdapter
    lateinit var localLayoutManager: RecyclerView.LayoutManager

    private val STRING_PREFERENCE = "Session"
    private val ACCOUNT_TOKEN = "userToken"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local)

        localRecyclerView = findViewById(R.id.localsRecyclerView)
        localAdapter = LocalAdapter(locals, this)
        localLayoutManager = GridLayoutManager(this, 2) as RecyclerView.LayoutManager
        localRecyclerView.adapter = localAdapter
        localRecyclerView.layoutManager = localLayoutManager

        //COLOCARLO EN EL LOGIN
        var preferences : SharedPreferences = this.getSharedPreferences(STRING_PREFERENCE,MODE_PRIVATE)

        NewsApi.requestLocal("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6ImNoZW1hbG9uc285NiIsIm5iZiI6MTU1NjIwODI5OCwiZXhwIjoxNTU2MjExODk4LCJpYXQiOjE1NTYyMDgyOTgsImlzcyI6Imh0dHA6Ly82NC4yMDIuMTg2LjIxNS9BUElNZWthV2FzaCIsImF1ZCI6Imh0dHA6Ly82NC4yMDIuMTg2LjIxNS9BUElNZWthV2FzaCJ9.uFEhfff5bP2xl841Lyd4reD6ZXs5clckOIh40qY-scU",
            {response -> handleResponse(response)},
            {error -> handleError(error)})
    }

    private fun handleResponse(response: LocalsResponse?) {
        val error = response!!.Error
        if (error.equals(false)) {
            Log.d("CatchUp", response.Message)
            return
        }

        locals = response.Data!!
        Log.d("CatchUp", "Found ${locals.size} articles")
        localAdapter.locals = locals
        localAdapter.notifyDataSetChanged()
    }

    private fun handleError(anError: ANError?) {
        Log.d("CatchUp", anError!!.message)
    }
}
