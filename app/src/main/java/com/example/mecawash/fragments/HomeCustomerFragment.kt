package com.example.mecawash.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mecawash.R
import com.example.mecawash.activities.HomeCustomerActivity
import com.example.mecawash.activities.LoginCustomerActivity
import com.example.mecawash.activities.ReservationServiceActivity
import com.example.mecawash.activities.ServiceClientActivity
import kotlinx.android.synthetic.main.fragment_home_customer.*


class HomeCustomerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_home_customer, container, false)
    }



}
