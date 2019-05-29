package com.example.mecawash.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.mecawash.R
import com.example.mecawash.fragments.CustomerFragment
import com.example.mecawash.fragments.ProfileFragment
import com.example.mecawash.fragments.RequestFragment
import com.example.mecawash.fragments.ServiceFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home_provider.*

class HomeProviderActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        item ->
        return@OnNavigationItemSelectedListener navigateTo(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_provider)

        navigationProvider.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigationProvider.selectedItemId = R.id.navigation_provider_solicitud
    }

    private fun fragmentFor(item: MenuItem): Fragment{
        when(item.itemId){
            R.id.navigation_provider_solicitud -> {
                return RequestFragment()
            }
            R.id.navigation_provider_servios -> {
                return ServiceFragment()
            }
            R.id.navigation_provider_clientes -> {
                return CustomerFragment()
            }
            R.id.navigation_provider_perfil -> {
                return ProfileFragment()
            }
        }
        return RequestFragment()
    }

    private fun navigateTo(item: MenuItem): Boolean{
        item.setChecked(true)

        return supportFragmentManager
            .beginTransaction()
            .replace(R.id.content,fragmentFor(item))
            .commit() > 0
    }

}
