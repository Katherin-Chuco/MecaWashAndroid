package com.example.mecawash.models

import android.os.Bundle

data class Local(
    val LocalId: Int,
    val Address: String,
    val DistrictId: Int,
    val ProvinceId: Int,
    val DepartmentId: Int,
    val ProviderId:Int,
    val Punctuation: Int,
    val Status: String,
    val Telefono: String){

    companion object {
        fun from(bundle: Bundle): Local {
            return Local(
                //Source.from(bundle.getBundle("source")!!),
                bundle.getInt("LocalId")!!,
                bundle.getString("Address")!!,
                bundle.getInt("DistrictId")!!,
                bundle.getInt("ProvinceId")!!,
                bundle.getInt("DepartmentId")!!,
                bundle.getInt("ProviderId")!!,
                bundle.getInt("Punctuation")!!,
                bundle.getString("Status")!!,
                bundle.getString("Telefono")!!)
        }
    }

    fun toBundle() : Bundle {
        val bundle = Bundle()
        //bundle.putBundle("source", source.toBundle())
        bundle.putInt("LocalId", LocalId)
        bundle.putString("Address", Address)
        bundle.putInt("DistrictId", DistrictId)
        bundle.putInt("ProvinceId", ProvinceId)
        bundle.putInt("DepartmentId", DepartmentId)
        bundle.putInt("ProviderId", ProviderId)
        bundle.putInt("Punctuation", Punctuation)
        bundle.putString("Status", Status)
        bundle.putString("Telefono", Telefono)
        return bundle
    }

}
