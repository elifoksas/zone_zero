package com.elifoksas.zonezero.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.elifoksas.zonezero.adapter.DoctorsAdapter
import com.elifoksas.zonezero.databinding.FragmentHomeBinding
import com.elifoksas.zonezero.model.Doctors
import com.elifoksas.zonezero.viewmodel.HomeViewModel


class HomeFragment : Fragment(){

    private val viewModel : HomeViewModel by viewModels()
    private lateinit var adapter : DoctorsAdapter
    private lateinit var binding : FragmentHomeBinding
    var doctorsList = ArrayList<Doctors>()

    companion object{
        var checkBoxMan : Boolean = false
        var checkBoxWoman : Boolean = false
    }



    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.getDoctor()
        viewModel.getDoctors()
        viewModel.getDoctors2()

        viewModel.doctors.observe(viewLifecycleOwner, Observer {
            adapter.latestMessagesUpdate(it)
        })

        adapter = DoctorsAdapter(doctorsList)

        binding.recyclerView.adapter = adapter

        val checkBoxMale = binding.male
        val checkBoxFemale = binding.female
        checkBoxMale.setOnClickListener {
            if ((!checkBoxFemale.isChecked) && (!checkBoxMale.isChecked)){
                viewModel.getDoctor()
                viewModel.getDoctors()
                viewModel.getDoctors2()
                viewModel.doctors.value?.let { it1 -> adapter.latestMessagesUpdate(it1) }
            }
            else if(checkBoxMale.isChecked){
                checkBoxMan = true
                viewModel.getDoctor("male")
                viewModel.getDoctors()
                viewModel.getDoctors2()
                viewModel.doctors.value?.let { it1 -> adapter.latestMessagesUpdate(it1) }
                searchViewOnQuery()
            }
            checkBoxFemale.isChecked = false



        }
        checkBoxFemale.setOnClickListener {

            if ((!checkBoxFemale.isChecked) && (!checkBoxMale.isChecked)){
                viewModel.getDoctor()
                viewModel.getDoctors()
                viewModel.getDoctors2()
                viewModel.doctors.value?.let { it1 -> adapter.latestMessagesUpdate(it1) }
            }
            else if (checkBoxFemale.isChecked){
                checkBoxWoman = true

                viewModel.getDoctor("female")
                viewModel.getDoctors()
                viewModel.getDoctors2()
                viewModel.doctors.value?.let { it1 -> adapter.latestMessagesUpdate(it1) }
            }
            checkBoxMale.isChecked = false
        }

        searchViewOnQuery()
    }
    private fun searchViewOnQuery(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }


        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }




}