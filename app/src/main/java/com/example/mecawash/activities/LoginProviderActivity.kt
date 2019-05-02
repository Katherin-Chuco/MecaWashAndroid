package com.example.mecawash.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.error.ANError
import com.example.mecawash.R
import com.example.mecawash.network.LoginProviderResponse
import com.example.mecawash.network.NewsApi
import kotlinx.android.synthetic.main.activity_login_provider.*



class LoginProviderActivity : AppCompatActivity() {

    private val STRING_PREFERENCE = "Session"
    private val TOKEN = "Token"
    private val NOMBREPROVIDER = "NombreProvider"
    private val PROVIDERID = "ProviderId"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_provider)

        loginProviderButton.setOnClickListener {
            var usernameET = usernameEditText.text.toString()
            var passwordET = passwordEditText.text.toString()

            NewsApi.requestLoginProvider(usernameET,passwordET,
                {response -> handleResponse(response)},
                {error -> handleError(error)})
        }

    }



    private fun handleResponse(response: LoginProviderResponse?) {
        val error = response!!.Error
        if (error.equals(true)) {
            Log.d("MecaWash", response.Message)
            return
        }
        var preferences : SharedPreferences = this.getSharedPreferences(STRING_PREFERENCE,MODE_PRIVATE)

        val sp = preferences.edit()
        sp.putString(TOKEN, response.Data!!.Token)
        sp.putString(NOMBREPROVIDER, response.Data!!.Nombre)
        sp.putInt(PROVIDERID, response.Data!!.ProviderId)
        sp.apply()

        if (preferences.getBoolean(STRING_PREFERENCE, false)) {
            val localIntent = Intent(this, LocalActivity::class.java)
            startActivity(localIntent)
            finish()
        }

        val intento = Intent(this, LocalActivity::class.java)
        startActivity(intento)
    }

    private fun handleError(anError: ANError?) {
        Log.d("MecaWash", anError!!.message)
    }

}
