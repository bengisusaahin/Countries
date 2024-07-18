package com.bengisusahin.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bengisusahin.countries.R
import com.bengisusahin.countries.adapter.CountryAdapter
import com.bengisusahin.countries.databinding.FragmentFeedBinding
import com.bengisusahin.countries.viewmodel.FeedViewModel

class FeedFragment : Fragment() {

    private var _binding: FragmentFeedBinding? = null
    // This property is only valid between onCreateView and onDestroyView.

    private lateinit var viewModel: FeedViewModel
    // for empty list initialization in adapter we are using arrayListOf()
    private val countryAdapter = CountryAdapter(arrayListOf())

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*binding.btnToCountryFragment.setOnClickListener{
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }*/

        viewModel = ViewModelProvider(this@FeedFragment)[FeedViewModel::class.java]
        viewModel.refreshData()

        binding.rvCountryList.layoutManager = LinearLayoutManager(context)
        binding.rvCountryList.adapter = countryAdapter

        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner , Observer { countries ->
            countries?.let {
                binding.rvCountryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if(it){
                    binding.tvCountryError.visibility = View.VISIBLE
                }else{
                    binding.tvCountryError.visibility = View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if(it){
                    binding.pbCountryLoading.visibility = View.VISIBLE
                    binding.rvCountryList.visibility = View.GONE
                    binding.tvCountryError.visibility = View.GONE
                }else{
                    binding.pbCountryLoading.visibility = View.GONE
                }
            }
        })
    }
}