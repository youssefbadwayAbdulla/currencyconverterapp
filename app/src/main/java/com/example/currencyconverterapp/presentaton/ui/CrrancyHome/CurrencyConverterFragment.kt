package com.example.currencyconverterapp.presentaton.ui.CrrancyHome

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController

import com.example.currencyconverterapp.databinding.FragmentCurrencyConverterBinding
import com.example.currencyconverterapp.domain.model.CurrencyModel
import com.example.currencyconverterapp.presentaton.ui.adapters.CurrencyAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class CurrencyConverterFragment : Fragment(), CurrencyAdapter.OnItemClickListener {
    private lateinit var binding: FragmentCurrencyConverterBinding
    private val viewModel: CurrencyViewModel by sharedViewModel()
   var currencyList: CurrencyModel?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCurrencyConverterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConvert.setOnClickListener {
            viewModel.convert(
                binding.etFrom.text.toString(),
                binding.spFromCurrency.selectedItem.toString(),
                binding.spToCurrency.selectedItem.toString()
            )
        }
        with(viewModel) {
            conversion.observe(viewLifecycleOwner) { event ->
                when (event) {
                    is CurrencyViewModel.CurrencyEvent.Success -> {
                        binding.progressBar.isVisible = false
                        binding.tvResult.setTextColor(Color.BLACK)
                        binding.tvResult.text = event.resultText


                    }
                    is CurrencyViewModel.CurrencyEvent.Failure -> {
                        binding.progressBar.isVisible = false
                        binding.tvResult.setTextColor(Color.RED)
                        binding.tvResult.text = event.errorMessage

                    }
                    is CurrencyViewModel.CurrencyEvent.Loading -> {
                        binding.progressBar.isVisible = true

                    }
                    else -> Unit
                }
                conversionList.observe(viewLifecycleOwner) { event ->
                  binding.progressBarRv.isVisible = false
                   setupRecyclerView(event)
                  binding.progressBarRv.isVisible = true
                }
            }
        }

    }

    private fun setupRecyclerView(currencyList:List<CurrencyModel> ) {
        val adapter: CurrencyAdapter by lazy { CurrencyAdapter(currencyList, this) }
        adapter.setList(currencyList)
        binding.rvCurrencyConverter.adapter = adapter

    }

    override fun onClick(item: CurrencyModel) {
        findNavController().navigate(CurrencyConverterFragmentDirections.actionCurrencyConverterFragmentToCurrencyDetailsFragment())
    }
}