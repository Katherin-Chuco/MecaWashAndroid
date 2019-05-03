package com.example.mecawash.network

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import org.json.JSONObject
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONException
import java.util.*


class NewsApi{
    companion object {


        val baseUrl = "http://64.202.186.215/APIMekaWash"
        //val baseUrl = "http://localhost:50790"

        var loginProviderUrl = "$baseUrl/wamekawash/v1/loginprovider"

        fun getCustomerId(id: Int): String{
            return "${NewsApi.baseUrl}/wamekawash/v2/customers/$id"
        }

        fun getCarId(idcustomer:Int,idcar: Int):String{
            return "${NewsApi.baseUrl}/wamekawash/v7/customers/$idcustomer/cars/$idcar"
        }

        fun getLocals(id: Int) : String{
            return "${NewsApi.baseUrl}/wamekawash/v4/providers/$id/locals"
        }

        fun getRequests(id: Int):String{
            return "${NewsApi.baseUrl}/wamekawash/v6/locals/$id/reservations"
        }

        fun getChangeStatus(id: Int): String{
            return "${NewsApi.baseUrl}/wamekawash/v6/locals/$id/reservations"
        }

        fun requestChangeStatus(key: String, url: String,ReservationId:String,Status:String,MessageProvider:String,  responseHandler: (PostResponse?) -> Unit, errorHandler: (ANError?) -> Unit){

            AndroidNetworking.post(url)
                .addHeaders("Authorization",key)
                .addBodyParameter("ReservationId", ReservationId)
                .addBodyParameter("Status", Status)
                .addBodyParameter("MessageProvider", MessageProvider)
                .setTag("MecaWashAndroid")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(PostResponse::class.java,object : ParsedRequestListener<PostResponse> {
                    override fun onResponse(response: PostResponse?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError) {
                        errorHandler(anError)
                    }
                })
        }

        fun requestRequest(key: String, url: String, responseHandler: (RequestResponse?) -> Unit, errorHandler: (ANError?) -> Unit){
            AndroidNetworking.get(url)
                .addHeaders("Authorization", key)
                .setPriority(Priority.HIGH)
                .setTag("MecaWashAndroid")
                .build()
                .getAsObject(RequestResponse::class.java,
                    object : ParsedRequestListener<RequestResponse> {
                        override fun onResponse(response: RequestResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }

        fun requestCustomer(key: String, url: String, responseHandler: (CustomerResponse?) -> Unit, errorHandler: (ANError?) -> Unit){
            AndroidNetworking.get(url)
                .addHeaders("Authorization", key)
                .setPriority(Priority.HIGH)
                .setTag("MecaWashAndroid")
                .build()
                .getAsObject(CustomerResponse::class.java,
                    object : ParsedRequestListener<CustomerResponse> {
                        override fun onResponse(response: CustomerResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }

        fun requestCar(key: String, url: String, responseHandler: (CarResponse?) -> Unit, errorHandler: (ANError?) -> Unit) {
            AndroidNetworking.get(url)
                .addHeaders("Authorization", key)
                .setPriority(Priority.HIGH)
                .setTag("MecaWashAndroid")
                .build()
                .getAsObject(CarResponse::class.java,
                    object : ParsedRequestListener<CarResponse> {
                        override fun onResponse(response: CarResponse?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }
                    })
        }

        fun requestLocals(key: String, url: String, responseHandler: (LocalsResponse?) -> Unit, errorHandler: (ANError?) -> Unit) {
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