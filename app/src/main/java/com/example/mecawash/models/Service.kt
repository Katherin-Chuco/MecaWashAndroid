package com.example.mecawash.models

import android.os.Bundle

data class Service(val ServiceId:Int,
                   val Name:String,
                   val Detail:String,
                   val LocalId: Int,
                   val UrlPhoto:String,
                   val Cost: Double,
                   val Status: String,
                   val CategoryId: Int,
                   val NameCategory: String) {

    fun toBundle() : Bundle {
        val bundle = Bundle()

        bundle.putInt("ServiceId",ServiceId)
        bundle.putString("Name", Name)
        bundle.putString("Detail", Detail)
        bundle.putInt("LocalId", LocalId)
        bundle.putString("UrlPhoto", UrlPhoto)
        bundle.putDouble("Cost", Cost)
        bundle.putString("Status", Status)
        bundle.putInt("CategoryId", CategoryId)
        bundle.putString("NameCategory", NameCategory)

        return bundle
    }
}