package com.example.mecawash.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.error.ANError
import com.example.mecawash.R
import com.example.mecawash.network.LocalsResponse
import com.example.mecawash.network.LoginProviderResponse
import com.example.mecawash.network.NewsApi
import kotlinx.android.synthetic.main.activity_login_provider.*

class LoginProviderActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_provider)

        loginProviderButton.setOnClickListener {
            var usernameET = usernameEditText.text.toString()
            var passwordET = passwordEditText.text.toString()
            //val intento = Intent(this, LoginProviderActivity::class.java)
            //startActivity(intento)
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
        //locals = response.Data!!
        //Log.d("MecaWash", "Found ${locals.size} locals")
        //localsAdapter.locals = locals
        //localsAdapter.notifyDataSetChanged()
    }

    private fun handleError(anError: ANError?) {
        Log.d("MecaWash", anError!!.message)
    }

}
