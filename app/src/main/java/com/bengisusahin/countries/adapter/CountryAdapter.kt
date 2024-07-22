package com.bengisusahin.countries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bengisusahin.countries.R
import com.bengisusahin.countries.databinding.ItemCountryBinding
import com.bengisusahin.countries.model.Country
import com.bengisusahin.countries.view.FeedFragmentDirections
import com.bengisusahin.util.downloadFromUrl
import com.bengisusahin.util.placeHolderProgressBar

class CountryAdapter(val countryList: ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    class CountryViewHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root){

    }

    // adapter and layout connection
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = DataBindingUtil.inflate<ItemCountryBinding>(LayoutInflater.from(parent.context), R.layout.item_country,parent, false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.binding.country = countryList[position]
        /*
        holder.binding.tvCountryName.text = countryList[position].countryName
        holder.binding.tvCountryRegion.text = countryList[position].countryRegion

        holder.binding.root.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.iv.downloadFromUrl(countryList[position].imageUrl, placeHolderProgressBar(holder.binding.root.context))

         */
    }

    fun updateCountryList(newCountryList: List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}