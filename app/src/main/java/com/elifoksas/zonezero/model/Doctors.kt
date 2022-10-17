package com.elifoksas.zonezero.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Doctors(

    @SerializedName("full_name")
    val fullName : String,
    @SerializedName("user_status")
    val userStatus : String,
    @SerializedName("gender")
    val gender : String,
    @SerializedName("image")
    val image: Image,

    ) : Serializable
