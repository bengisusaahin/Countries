package com.bengisusahin.countries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bengisusahin.countries.model.Country
import com.bengisusahin.countries.service.CountryApiService
import com.bengisusahin.countries.service.CountryDatabase
import com.bengisusahin.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application) {

    private val countryApiService = CountryApiService()
    // CompositeDisposable is a convenient class for handling multiple disposable objects
    // use and dispose of them all at once
    // to avoid memory leaks
    // retrofit uses disposable objects
    private val disposable = CompositeDisposable()
    private var customPreferences = CustomSharedPreferences(getApplication())

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        getDataFromAPI()
    }

    fun getDataFromAPI(){
        countryLoading.value = true

        disposable.add(
            countryApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
//                        countries.value = t
//                        countryError.value = false
//                        countryLoading.value = false
                        storeInSQLite(t)
                    }

                    override fun onError(e: Throwable) {
                        countryError.value = true
                        countryLoading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun showCountries(countryList : List<Country>){
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }

    private fun storeInSQLite(countryList : List<Country>){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listLong = dao.insertAll(*countryList.toTypedArray()) // * is the spread operator list -> individual elements
            var i = 0
            while(i < countryList.size){
                countryList[i].uuid = listLong[i].toInt()
                i++
            }
            showCountries(countryList)
        }
        customPreferences.saveTime(System.nanoTime())
    }

}