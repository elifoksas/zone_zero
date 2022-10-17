package com.elifoksas.zonezero.service

import com.elifoksas.zonezero.model.Doctor
import com.elifoksas.zonezero.model.Doctors
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class DoctorApiService {

    private val BASE_URL = "https://www.mobillium.com/android/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(DoctorApi::class.java)

    fun getData() : Call<Doctor> {
        return api.getDoctors()
    }



}