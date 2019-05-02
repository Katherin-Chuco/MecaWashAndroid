package com.example.mecawash.network

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import org.json.JSONObject
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONException





class NewsApi{
    companion object {


        val baseUrl = "http://64.202.186.215/APIMekaWash"
        //val baseUrl = "http://localhost:50790"

        var loginProviderUrl = "$baseUrl/wamekawash/v1/loginprovider"

        fun getLocal(id: Int) : String{
            return "${NewsApi.baseUrl}/wamekawash/v4/providers/$id/locals"
        }

        fun requestLocal(key: String, url: String, responseHandler: (LocalsResponse?) -> Unit, errorHandler: (ANError?) -> Unit) {

            AndroidNetworking.get(url)
                .addHeaders("Authorization", key)
                .setPriority(Priority.HIGH)
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

        fun requestLoginProvider(Username: String, Password: String, responseHandler: (LoginProviderResponse?)-> Unit, errorHandler: (ANError?) -> Unit){


            var url = NewsApi.loginProviderUrl
            AndroidNetworking.post(url)
                .addBodyParameter("Username", Username)
                .addBodyParameter("Password", Password)
                .setTag("MecaWashAndroid")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(LoginProviderResponse::class.java,object : ParsedRequestListener<LoginProviderResponse> {
                    override fun onResponse(response: LoginProviderResponse?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError) {
                        errorHandler(anError)
                    }
                })
        }
    }
}