package com.example.mecawash.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.error.ANError

import com.example.mecawash.R
import com.example.mecawash.network.LocalsResponse
import com.example.mecawash.network.NewsApi
import kotlinx.android.synthetic.main.fragment_provider_client_info.*


val TOKEN_PROVIDER_ACTIVITY: String = "Token"
private val STRING_PREFERENCE = "Session"

class ProviderClientInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_provider_client_info, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val result = this.activity!!.getSharedPreferences(STRING_PREFERENCE, AppCompatActivity.MODE_PRIVATE)
        var token = "Bearer " + result.getString(TOKEN_PROVIDER_ACTIVITY, "")

        val url: String = NewsApi.getLocal(1)


        NewsApi.requestLocal(token, url,
            {response -> handleResponse(response)},
            {error -> handleError(error)})


    }

    private fun handleResponse(response: LocalsResponse?) {
        val error = response!!.Error
        if (error.equals(true)) {
            Log.d("MecaWash", response.Message)
            return
        }

        //ratingProvider.rating = response.Data!![0].Punctuation.toFloat()

        directionValueTextView.text = response.Data!![0].Address
        phoneValueTextView.text = response.Data!![0].Telefono


    }

    private fun handleError(anError: ANError?) {
        Log.d("MecaWash", anError!!.message)
    }
}
