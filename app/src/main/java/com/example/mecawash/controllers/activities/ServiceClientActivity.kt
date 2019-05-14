package com.example.mecawash.controllers.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity;
import com.example.mecawash.R

import kotlinx.android.synthetic.main.activity_service_client.*

class ServiceClientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_client)
        setSupportActionBar(toolbar)

    }

    fun reservation(view: View) {
        val intent = Intent(this, ReservationServiceActivity::class.java)
        startActivity(intent)
        finish()
    }

}
