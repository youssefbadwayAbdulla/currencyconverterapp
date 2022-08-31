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
import com.example.currencyconverterapp.presentaton.ui.CrrancyHome.CurrencyViewModel.CurrencyEvent
import com.example.currencyconverterapp.presentaton.ui.CrrancyHome.CurrencyViewModel.CurrencyEvent.*
import com.example.currencyconverterapp.presentaton.ui.adapters.CurrencyAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class CurrencyConverterFragment : Fragment(), CurrencyAdapter.OnItemClickListener {
    private lateinit var binding: FragmentCurrencyConverterBinding
    private val viewModel: CurrencyViewModel by sharedViewModel()
    private val adapter: CurrencyAdapter by lazy { CurrencyAdapter(listOf(), this) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyConverterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.currencyList.observe(viewLifecycleOwner) { list ->
            handleListResult(list)
        }
        binding.btnConvert.setOnClickListener {
            viewModel.getRates(
                binding.etFrom.text.toString(),
                binding.spFromCurrency.selectedItem.toString(),
                binding.spToCurrency.selectedItem.toString()
            )
        }
        viewModel.currencyEvent.observe(viewLifecycleOwner) {
            handleResult(it)
        }


    }

    private fun handleListResult(list: List<CurrencyModel>) {
        setupRecyclerView(list)
    }

    private fun handleResult(event: CurrencyEvent<String>) {
        when (event) {
            is Success -> {
                binding.progressBar.isVisible = false
                binding.tvResult.setTextColor(Color.BLACK)
                binding.tvResult.text = event.data

            }
            is Failure -> {
                binding.progressBar.isVisible = false
                binding.tvResult.setTextColor(Color.RED)
                binding.tvResult.text = event.errorMessage

            }
            is Loading -> {
                binding.progressBar.isVisible = true

            }
            else -> {}
        }
    }

    private fun setupRecyclerView(currencyList: List<CurrencyModel>) {
        adapter.setList(currencyList)
        binding.rvCurrencyConverter.adapter = adapter
    }

    override fun onClick(item: CurrencyModel) {
        findNavController().navigate(
            CurrencyConverterFragmentDirections.actionCurrencyConverterFragmentToCurrencyDetailsFragment(
                item
            )
        )

    }
}