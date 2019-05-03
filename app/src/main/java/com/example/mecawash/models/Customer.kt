package com.example.mecawash.models

data class Customer(val CustomerId: Int,
                    val Names: String,
                    val LastNames: String,
                    val DocumentIdentity:String,
                    val Password: String,
                    val BirthdayDate: String,
                    val Username: String,
                    val Status: String,
                    val DepartmentId: Int,
                    val ProvinceId: Int,
                    val DistrictId: Int,
                    val Phone: String) {
}