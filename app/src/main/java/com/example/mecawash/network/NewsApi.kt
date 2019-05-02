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

        var loginProviderUrl = "$baseUrl/wamekawash/v4/loginprovider"

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

        fun requestLoginProvider(username: String, password: String, responseHandler: (LoginProviderResponse?)-> Unit, errorHandler: (ANError?) -> Unit){

            val jsonObject = JSONObject()
            try {
                jsonObject.put("Username", username)
                jsonObject.put("Password", password)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            var url = NewsApi.loginProviderUrl
            AndroidNetworking.post(url)
                .addJSONObjectBody(jsonObject)
                .setTag("MecaWashAndroid")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(LoginProviderResponse::class.java,
                    object : ParsedRequestListener<LoginProviderResponse> {
                        override fun onResponse(response: LoginProviderResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }
    }
}