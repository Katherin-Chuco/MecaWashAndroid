package com.example.mecawash.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mecawash.R
import com.example.mecawash.fragments.FavoriteCustomerFragment
import com.example.mecawash.fragments.HomeCustomerFragment
import com.example.mecawash.fragments.ProfileCustomerFragment
import com.example.mecawash.fragments.RequestCustomerFragment
import kotlinx.android.synthetic.main.activity_home_customer.*

class HomeCustomerActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        return@OnNavigationItemSelectedListener navigateTo(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_customer)

        navigationCustomer.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navigationCustomer.selectedItemId = R.id.navigation_customer_home

    }

    private fun getFragmentFor(item: MenuItem): Fragment {
        when(item.itemId) {
            R.id.navigation_customer_home -> { return HomeCustomerFragment() }
            R.id.navigation_customer_requests -> { return RequestCustomerFragment() }
            R.id.navigation_customer_favorite -> { return FavoriteCustomerFragment() }
            R.id.navigation_customer_profile -> { return ProfileCustomerFragment() }
        }

        return HomeCustomerFragment()
    }

    private fun navigateTo(item: MenuItem): Boolean {
        item.setChecked(true)

        return supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, getFragmentFor(item))
            .commit() > 0
    }

    fun onService (view: View){

        val intent = Intent(this, ProviderClientActivity::class.java)
        startActivity(intent)
    }
}
