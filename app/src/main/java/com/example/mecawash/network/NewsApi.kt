package com.example.mecawash.network

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener

class NewsApi{
    companion object {


        val baseUrl = "http://64.202.186.215/APIMekaWash"
        var localsUrl = "$baseUrl/wamekawash/v4"

        fun requestLocal(key: String,
                         responseHandler: (LocalsResponse?) -> Unit,
                         errorHandler: (ANError?) -> Unit) {
            AndroidNetworking.get(NewsApi.localsUrl)
                .addHeaders("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6ImNoZW1hbG9uc285NiIsIm5iZiI6MTU1NTgwNTI4MiwiZXhwIjoxNTU1ODA4ODgyLCJpYXQiOjE1NTU4MDUyODIsImlzcyI6Imh0dHA6Ly82NC4yMDIuMTg2LjIxNS9BUElNZWthV2FzaCIsImF1ZCI6Imh0dHA6Ly82NC4yMDIuMTg2LjIxNS9BUElNZWthV2FzaCJ9.H-BVhRD784KpGOn46YSA3yroY9gEsTmyDFUfgsjc3gU")
                //.addQueryParameter("apiKey", key)
                //.addQueryParameter("language", "en")
                .setPriority(Priority.LOW)
                .setTag("MecaWashAndroid")
                .build()
                .getAsObject(LocalsResponse::class.java,
                    object : ParsedRequestListener<LocalsResponse> {
                        override fun onResponse(response: LocalsResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }
    }
}