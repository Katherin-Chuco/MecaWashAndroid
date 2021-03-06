package com.example.mecawash.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androidnetworking.error.ANError
import com.example.mecawash.R
import com.example.mecawash.network.LoginCustomerResponse
import com.example.mecawash.network.NewsApi
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.activity_login_customer.*

class LoginCustomerActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {
    private val TAG = "GoogleSignIn"
    private lateinit var gClient: GoogleSignInClient
    private val STRING_PREFERENCE = "Session"
    private val TOKEN = "Token"
    private val NOMBRECUSTOMER = "NombreCustomer"
    private val CUSTOMERID = "CustomerId"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_customer)

        val bygoogle = findViewById<SignInButton>(R.id.sign_in_button)
        bygoogle.setSize(SignInButton.SIZE_STANDARD)

        googleSignInSetup()
        bygoogle.setOnClickListener {
            Toast.makeText(applicationContext, "Login", Toast.LENGTH_SHORT).show();
            val login = gClient.signInIntent
            startActivityForResult(login,1)
        }


        loginCustomerButton.setOnClickListener {
            var usernameET = usernameEditText.text.toString()
            var passwordET = passwordEditText.text.toString()

            usernameET = "chemalonso96"
            passwordET = "12345678"

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


        val intento = Intent(this, HomeCustomerActivity::class.java)
        startActivity(intento)
    }

    private fun handleError(anError: ANError?) {
        Log.d("MecaWash", anError!!.message)
    }


    override fun onStart() {
        super.onStart()
        val user = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(user)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val user = task.getResult(ApiException::class.java)
            updateUI(user)
        }

    }
    private fun googleSignInSetup(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        gClient = GoogleSignIn.getClient(this,gso)

    }
    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.e(TAG, "onConnectionFailed():" + connectionResult);
        Toast.makeText(applicationContext, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if(account!=null){
            Toast.makeText(applicationContext, account.displayName+"\n"+account.email, Toast.LENGTH_LONG).show();
            //val intento = Intent(this, ProviderClientActivity::class.java)
            //startActivity(intento)
        }

    }
}