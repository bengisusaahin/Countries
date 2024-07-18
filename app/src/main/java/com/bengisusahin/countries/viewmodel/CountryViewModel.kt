package com.bengisusahin.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bengisusahin.countries.model.Country

class CountryViewModel : ViewModel(){
    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(){
        val country = Country("Turkey", "Asia", "Ankara", "TRY", "Turkish", "www.google.com")
        countryLiveData.value = country
    }
}