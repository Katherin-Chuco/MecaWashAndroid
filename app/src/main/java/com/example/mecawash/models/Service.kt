package com.example.mecawash.models

data class Service(val ServiceId:Int,
                   val Name:String,
                   val Detail:String,
                   val LocalId: Int,
                   val UrlPhoto:String,
                   val Cost: Double,
                   val Status: String,
                   val CategoryId: Int,
                   val NameCategory: String) {
}