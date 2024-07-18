package com.bengisusahin.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bengisusahin.countries.R
import com.bengisusahin.countries.databinding.FragmentCountryBinding
import com.bengisusahin.countries.viewmodel.CountryViewModel

class CountryFragment : Fragment() {

    private var _binding: FragmentCountryBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CountryViewModel
    private var countryUuid = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCountryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this@CountryFragment)[CountryViewModel::class.java]
        viewModel.getDataFromRoom()


        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                binding.tvCountryName.text = country.countryName
                binding.tvCountryRegion.text = country.countryRegion
                binding.tvCountryCapital.text = country.countryCapital
                binding.tvCountryCurrency.text = country.countryCurrency
                binding.tvCountryLanguage.text = country.countryLanguage
            }
        })
    }
}