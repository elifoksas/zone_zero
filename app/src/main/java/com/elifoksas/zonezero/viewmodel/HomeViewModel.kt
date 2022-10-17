package com.elifoksas.zonezero.viewmodel

import android.os.Debug
import android.util.Log
import android.widget.CheckBox
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elifoksas.zonezero.model.Doctor
import com.elifoksas.zonezero.model.Doctors
import com.elifoksas.zonezero.service.DoctorApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class HomeViewModel : ViewModel(){

    val doctor = MutableLiveData<Doctor>()
    val doctors = MutableLiveData<List<Doctors>>()
    val list : ArrayList<Doctors> = arrayListOf()
    private val doctorApiService = DoctorApiService()

    fun getDoctors() : LiveData<Doctor> {
        return doctor
    }
    fun getDoctors2() : LiveData<List<Doctors>> {
        return doctors
    }

    fun getDoctor(){

       doctorApiService.getData().enqueue(object : Callback<Doctor>{
           override fun onResponse(call: Call<Doctor>, response: Response<Doctor>) {
               val responseBody = response.body()!!
               doctor.value = response.body()
               doctors.value = doctor.value?.doctor
           }

           override fun onFailure(call: Call<Doctor>, t: Throwable) {
               Log.d("Hata",t.message.toString())
           }

       })

    }
    fun getDoctor(checkBox: String){

        doctorApiService.getData().enqueue(object : Callback<Doctor>{
            override fun onResponse(call: Call<Doctor>, response: Response<Doctor>) {
                val responseBody = response.body()!!
                list.clear()
                responseBody.doctor.forEach {

                    if (it.gender == checkBox){

                        list.add(it)
                    }
                }
                doctors.value = list
            }

            override fun onFailure(call: Call<Doctor>, t: Throwable) {
                Log.d("Hata",t.message.toString())
            }

        })

    }

}