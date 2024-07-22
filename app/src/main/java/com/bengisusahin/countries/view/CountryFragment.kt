package com.bengisusahin.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bengisusahin.countries.R
import com.bengisusahin.countries.databinding.FragmentCountryBinding
import com.bengisusahin.countries.viewmodel.CountryViewModel
import com.bengisusahin.util.downloadFromUrl
import com.bengisusahin.util.placeHolderProgressBar

class CountryFragment : Fragment() {
//
//    private var _binding: FragmentCountryBinding? = null
//    private val binding get() = _binding!!

    private lateinit var viewModel: CountryViewModel
    private var countryUuid = 0
    private lateinit var dataBinding: FragmentCountryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_country, container, false)
        val view = dataBinding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProvider(this@CountryFragment)[CountryViewModel::class.java]
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
//                binding.tvCountryName.text = country.countryName
//                binding.tvCountryRegion.text = country.countryRegion
//                binding.tvCountryCapital.text = country.countryCapital
//                binding.tvCountryCurrency.text = country.countryCurrency
//                binding.tvCountryLanguage.text = country.countryLanguage
//                context?.let {
//                    binding.ivCountryImage.downloadFromUrl(country.imageUrl, placeHolderProgressBar(it))
//                }
                dataBinding.selectedCountry = country
            }
        })
    }
}