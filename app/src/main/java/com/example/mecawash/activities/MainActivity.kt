package com.example.mecawash.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mecawash.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        providerImageView.setOnClickListener {
            val intento = Intent(this, LoginProviderActivity::class.java)
            startActivity(intento)
        }

        customerImageView.setOnClickListener {
            val intento = Intent(this, LoginCustomerActivity::class.java)
            startActivity(intento)
        }
    }


}
