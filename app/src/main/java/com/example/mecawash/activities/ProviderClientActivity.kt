package com.example.mecawash.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.example.mecawash.R

import kotlinx.android.synthetic.main.activity_provider_client.*

class ProviderClientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_client)
        setSupportActionBar(toolbar)

    }

    fun onGo(view: View) {
        val intent = Intent(this, ServiceClientActivity::class.java)
        startActivity(intent)
        finish()
    }
}
