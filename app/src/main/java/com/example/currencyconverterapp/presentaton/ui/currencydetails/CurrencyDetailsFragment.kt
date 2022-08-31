package com.example.currencyconverterapp.presentaton.ui.currencydetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.currencyconverterapp.databinding.FragmentCurrencyDetailsBinding


class CurrencyDetailsFragment : Fragment() {
    private lateinit var binding: FragmentCurrencyDetailsBinding

    // get the arguments from the Registration fragment
    private val args: CurrencyDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyDetailsBinding.inflate(inflater)
        return binding.root
        // Inflate the layout for this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
//            binding.data = DetailsFragmentArgs.fromBundle(it).dataModel
            val currencyData = args.data
            binding.textViewName.text = currencyData.name
            binding.textViewPrice.text = currencyData.value.toString()
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(CurrencyDetailsFragmentDirections.actionCurrencyDetailsFragmentToCurrencyConverterFragment())
        }

    }
}