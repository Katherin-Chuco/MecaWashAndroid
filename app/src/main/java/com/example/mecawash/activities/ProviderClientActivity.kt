package com.example.mecawash.activities

import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.androidnetworking.error.ANError
import com.example.mecawash.R
import com.example.mecawash.fragments.ProviderClientInfoFragment
import com.example.mecawash.fragments.ProviderClientServicesFragment
import com.example.mecawash.network.LocalsResponse
import com.example.mecawash.network.NewsApi

import kotlinx.android.synthetic.main.activity_provider_client.*
import kotlinx.android.synthetic.main.content_provider_client.*


val TOKEN_PROVIDER__CLIENT_ACTIVITY: String = "Token"
private val STRING_PREFERENCE = "Session"

class ProviderClientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_client)
        //setSupportActionBar(toolbar)

        val result = getSharedPreferences(STRING_PREFERENCE, AppCompatActivity.MODE_PRIVATE)
        var token = "Bearer " + result.getString(TOKEN_PROVIDER__CLIENT_ACTIVITY, "")

        val url: String = NewsApi.getLocal(1)

        NewsApi.requestLocal(token, url,
            {response -> handleResponse(response)},
            {error -> handleError(error)})

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ProviderClientInfoFragment(), "Información")
        adapter.addFragment(ProviderClientServicesFragment(), "Servicios")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

    }

    private fun handleResponse(response: LocalsResponse?) {
        val error = response!!.Error
        if (error.equals(true)) {
            Log.d("MecaWash", response.Message)
            return
        }

        ratingBarClient.rating = response.Data!![0].Punctuation.toFloat()
        //ratingProvider.rating = response.Data!![0].Punctuation.toFloat()

    }

    private fun handleError(anError: ANError?) {
        Log.d("MecaWash", anError!!.message)
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

