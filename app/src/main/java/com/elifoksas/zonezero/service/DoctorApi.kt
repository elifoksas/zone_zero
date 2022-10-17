package com.elifoksas.zonezero.service
import com.elifoksas.zonezero.model.Doctor
import com.elifoksas.zonezero.model.Doctors
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface DoctorApi {
    @Headers("Content-Type: application/json")
    @GET("doctors.json")
    fun getDoctors() : Call<Doctor>
}