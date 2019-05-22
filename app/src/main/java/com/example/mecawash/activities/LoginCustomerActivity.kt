package com.example.mecawash.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.error.ANError
import com.example.mecawash.R
import com.example.mecawash.network.LoginCustomerResponse
import com.example.mecawash.network.LoginProviderResponse
import com.example.mecawash.network.NewsApi
import kotlinx.android.synthetic.main.activity_login_customer.*

class LoginCustomerActivity : AppCompatActivity() {

    private val STRING_PREFERENCE = "Session"
    private val TOKEN = "Token"
    private val NOMBRECUSTOMER = "NombreCustomer"
    private val CUSTOMERID = "CustomerId"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_customer)


        loginCustomerButton.setOnClickListener {
            var usernameET = usernameEditText.text.toString()
            var passwordET = passwordEditText.text.toString()

            NewsApi.requestLoginCustomer(usernameET,passwordET,
                {response -> handleResponse(response)},
                {error -> handleError(error)})
        }
    }

    private fun handleResponse(response: LoginCustomerResponse?) {
        val error = response!!.Error
        if (error.equals(true)) {
            Log.d("MecaWash", response.Message)
            return
        }
        var preferences : SharedPreferences = this.getSharedPreferences(STRING_PREFERENCE,MODE_PRIVATE)

        val sp = preferences.edit()
        sp.putString(TOKEN, response.Data!!.Token)
        sp.putString(NOMBRECUSTOMER, response.Data!!.Nombre)
        sp.putInt(CUSTOMERID, response.Data!!.CustomerId)
        sp.apply()

        //if (preferences.getBoolean(STRING_PREFERENCE, false)) {
            val homeClientIntent = Intent(this, ProviderClientActivity::class.java)
            startActivity(homeClientIntent)
            finish()
        //}
    }

    private fun handleError(anError: ANError?) {
        Log.d("MecaWash", anError!!.message)
    }
}
