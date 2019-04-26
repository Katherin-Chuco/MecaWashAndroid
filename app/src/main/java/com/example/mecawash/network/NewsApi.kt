package com.example.mecawash.network

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener

class NewsApi{
    companion object {


        val baseUrl = "http://64.202.186.215/APIMekaWash"

        //var localsUrl = "$baseUrl/wamekawash/v4"


        fun getLocal(id: Int) : String{
            return "${NewsApi.baseUrl}/wamekawash/v4/providers/$id/locals"
        }

        fun requestLocal(key: String, url: String, responseHandler: (LocalsResponse?) -> Unit, errorHandler: (ANError?) -> Unit) {
            AndroidNetworking.get(url)
                .addHeaders("Authorization", key)
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