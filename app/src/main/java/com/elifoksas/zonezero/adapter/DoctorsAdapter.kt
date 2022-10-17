package com.elifoksas.zonezero.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.elifoksas.zonezero.R
import com.elifoksas.zonezero.model.Doctor
import com.elifoksas.zonezero.model.Doctors
import com.elifoksas.zonezero.view.HomeFragment
import com.elifoksas.zonezero.view.HomeFragmentDirections
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class DoctorsAdapter(private val doctorsList : ArrayList<Doctors>) : RecyclerView.Adapter<DoctorsAdapter.DoctorsViewHolder>() ,Filterable{
    var userFilterList = ArrayList<Doctors>()
    lateinit var navController: NavController
    private val homeFragment: HomeFragment = HomeFragment()

    init {
        userFilterList = doctorsList
    }
    class DoctorsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.doctor_image)
        val text : TextView = itemView.findViewById(R.id.doctor_name)
        val cardView : CardView = itemView.findViewById(R.id.doctor_image_card_view)
        val doctorItem : RelativeLayout = itemView.findViewById(R.id.doctor_item_RL)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsViewHolder {
        Log.d("asd",doctorsList.size.toString())
        val view = LayoutInflater.from(parent.context).inflate(R.layout.doctor_item,parent,false)
        return DoctorsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorsViewHolder, position: Int) {

        Picasso.get().load(userFilterList[position].image.url).into(holder.image)
        holder.text.text = userFilterList[position].fullName


        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                userFilterList[position].fullName,
                userFilterList[position].image.url,
                userFilterList[position].userStatus
            )
            navController = Navigation.findNavController(it)
            navController.navigate(action)
        }



    }

    override fun getItemCount(): Int {
        Log.d("size",doctorsList.size.toString())
        return userFilterList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun latestMessagesUpdate(doctorsLists: List<Doctors>){
        doctorsList.clear()
        doctorsList.addAll(doctorsLists)
        doctorsLists.forEach {
            Log.d("gender",it.gender)
        }
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    userFilterList = doctorsList
                } else {
                    val resultList = ArrayList<Doctors>()
                    for (row in userFilterList) {
                        if (HomeFragment.checkBoxMan){
                            if (row.fullName.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT)) && row.gender.contains("male")) {

                                resultList.add(row)
                            }
                        }
                        else if(HomeFragment.checkBoxWoman){
                            if (row.fullName.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT)) && row.gender.contains("female")) {

                                resultList.add(row)
                            }
                        }
                        else{
                            if (row.fullName.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {

                                resultList.add(row)
                            }
                        }

                    }
                    userFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = userFilterList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                userFilterList = results?.values as ArrayList<Doctors>
                notifyDataSetChanged()
            }

        }
    }
}