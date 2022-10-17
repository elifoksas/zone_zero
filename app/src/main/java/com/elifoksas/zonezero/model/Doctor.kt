package com.elifoksas.zonezero.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Doctor(
    @SerializedName("doctors")
    val doctor : List<Doctors>
): Serializable
