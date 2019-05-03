package com.example.mecawash.network

import com.example.mecawash.models.Service

class ServiceResponse {
    var Data: ArrayList<Service>? = null
    var Error: Boolean = false
    var Message: String? = null
}