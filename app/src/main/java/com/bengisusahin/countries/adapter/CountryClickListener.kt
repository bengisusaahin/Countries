package com.bengisusahin.countries.adapter

import android.view.View

interface CountryClickListener {
    fun onCountryClicked(v: View, uuid: Int)
}