package com.bengisusahin.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bengisusahin.countries.model.Country

class FeedViewModel : ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val country = Country("Turkey", "Asia", "Ankara", "TRY", "Turkish", "www.google.com")
        val country2 = Country("Germany", "Europe", "Berlin", "EUR", "German", "www.google.com")
        val country3 = Country("USA", "America", "Washington", "USD", "English", "www.google.com")
        val countryList = arrayListOf<Country>(country, country2, country3)
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }
}