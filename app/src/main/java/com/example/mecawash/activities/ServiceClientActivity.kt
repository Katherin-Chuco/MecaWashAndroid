package com.example.mecawash.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.example.mecawash.R
import com.example.mecawash.models.Service

import kotlinx.android.synthetic.main.activity_service_client.*
import kotlinx.android.synthetic.main.content_service_client.*

class ServiceClientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_client)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        intent?.extras?.apply {

            titleTextView.text = this.getString("Name")
            descriptionTextView.text = this.getString("Detail")
            categoryTextView.text = this.getString("NameCategory")
        }

        reservationButton.setOnClickListener { view ->
            val context = view.context
            context.startActivity(Intent(context, ReservationServiceActivity::class.java).putExtras(intent?.extras))
        }
    }



}
