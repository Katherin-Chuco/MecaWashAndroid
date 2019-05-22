package com.example.mecawash.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.mecawash.R
import com.example.mecawash.fragments.ProviderClientInfoFragment
import com.example.mecawash.fragments.ProviderClientServicesFragment

import kotlinx.android.synthetic.main.activity_provider_client.*
import kotlinx.android.synthetic.main.content_provider_client.*

class ProviderClientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_client)
        //setSupportActionBar(toolbar)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ProviderClientInfoFragment(), "Información")
        adapter.addFragment(ProviderClientServicesFragment(), "Servicios")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

    }

    fun onGo(view: View) {
        val intent = Intent(this, ServiceClientActivity::class.java)
        startActivity(intent)
        finish()
    }

    class ViewPagerAdapter (supportFragmentManager: FragmentManager) : FragmentStatePagerAdapter(supportFragmentManager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList.get(position)
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }
    }
}

