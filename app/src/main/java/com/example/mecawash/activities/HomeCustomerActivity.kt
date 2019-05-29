package com.example.mecawash.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.mecawash.R

class HomeCustomerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_home)

        (findViewById<ImageView>(R.id.imageService01)).setOnClickListener {
            val intent = Intent(applicationContext, ServiceCustomerActivity::class.java)
            startActivity(intent)
        }

        (findViewById<ImageView>(R.id.imageService02)).setOnClickListener {
            val intent = Intent(applicationContext, ServiceCustomerActivity::class.java)
            startActivity(intent)
        }

        (findViewById<ImageView>(R.id.imageService03)).setOnClickListener {
            val intent = Intent(applicationContext, ServiceCustomerActivity::class.java)
            startActivity(intent)
        }

        (findViewById<ImageView>(R.id.imageService04)).setOnClickListener {
            val intent = Intent(applicationContext, ServiceCustomerActivity::class.java)
            startActivity(intent)
        }
    }
}
