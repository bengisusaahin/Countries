package com.bengisusahin.countries.service

import com.bengisusahin.countries.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryApi {

    // https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    // single is a type of observable that emits only one value and then completes
    fun getCountries() : Single<List<Country>>
}