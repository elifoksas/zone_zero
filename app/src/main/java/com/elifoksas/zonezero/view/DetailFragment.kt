package com.elifoksas.zonezero.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.elifoksas.zonezero.R
import com.elifoksas.zonezero.databinding.FragmentDetailBinding
import com.elifoksas.zonezero.databinding.FragmentHomeBinding
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val doctorName = DetailFragmentArgs.fromBundle(it).doctorName
            val doctorImage = DetailFragmentArgs.fromBundle(it).doctorImage
            val doctorStatus = DetailFragmentArgs.fromBundle(it).doctorStatus

            binding.doctorName.text = doctorName

            Picasso.get().load(doctorImage).into(binding.doctorImage)
            if (doctorStatus == "premium"){
                binding.appointmentTV.text = "Randevu Al"
                binding.premium.text = doctorStatus
            }
            else{
                binding.appointmentTV.text = "Premium Paket Al"
                binding.premium.text = ""
            }
        }
        binding.randevuCL.setOnClickListener {
            if (binding.premium.text == "premium"){
                val action = DetailFragmentDirections.actionDetailFragmentToAppointmentFragment()
                Navigation.findNavController(it).navigate(action)
            }
            else
            {
                val action = DetailFragmentDirections.actionDetailFragmentToPaymentFragment2()
                Navigation.findNavController(it).navigate(action)
            }


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

}