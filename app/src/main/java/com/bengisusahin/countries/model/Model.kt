package com.bengisusahin.countries.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country(
    @ColumnInfo("name")
    @SerializedName("name")
    val countryName: String?,
    @ColumnInfo("region")
    @SerializedName("region")
    val countryRegion: String?,
    @ColumnInfo("capital")
    @SerializedName("capital")
    val countryCapital: String?,
    @ColumnInfo("currency")
    @SerializedName("currency")
    val countryCurrency: String?,
    @ColumnInfo("language")
    @SerializedName("language")
    val countryLanguage: String?,
    @ColumnInfo("flag")
    @SerializedName("flag")
    val imageUrl: String?
){
    // it is not in constructor because we don't want to ask about it when we create a country object
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}