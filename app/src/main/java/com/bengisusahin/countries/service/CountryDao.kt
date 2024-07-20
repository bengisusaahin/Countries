package com.bengisusahin.countries.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bengisusahin.countries.model.Country

@Dao
interface CountryDao {

    // Data Access Object

    // vararg -> variable arguments -> multiple country like list but one by one
    // long list will return the primary key id of the inserted item
    @Insert
    suspend fun insertAll(vararg countries: Country) : List<Long>


    @Query("SELECT * FROM country")
    suspend fun getAllCountries() : List<Country>

    @Query("Select * FROM country WHERE uuid = :countryId")
    suspend fun getCountry(countryId: Int) : Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}