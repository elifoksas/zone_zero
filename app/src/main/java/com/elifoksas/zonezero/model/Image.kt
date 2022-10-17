package com.elifoksas.zonezero.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Image(

    @SerializedName("url")
    val url : String
) : Serializable