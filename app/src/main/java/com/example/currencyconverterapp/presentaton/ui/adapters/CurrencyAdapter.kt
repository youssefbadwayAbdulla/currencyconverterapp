package com.example.currencyconverterapp.presentaton.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverterapp.R
import com.example.currencyconverterapp.databinding.CurrencyItemBinding
import com.example.currencyconverterapp.domain.model.CurrencyModel

class CurrencyAdapter(
    private var currencyList: List<CurrencyModel>,
    private val itemClick:OnItemClickListener
) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {
    private lateinit var binding: CurrencyItemBinding
    fun setList(currencyList: List<CurrencyModel>) {
        this.currencyList = currencyList
        notifyDataSetChanged()
    }

    inner class CurrencyViewHolder(binding: CurrencyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                itemClick.onClick(currencyList[adapterPosition])
            }
        }
        fun bind(currencyList: CurrencyModel) = with(binding) {
            tvCurrencyName.text=currencyList.name


        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = CurrencyItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
     holder.bind(currencyList[position])
    }

    override fun getItemCount(): Int {
       return currencyList.size
    }

    interface OnItemClickListener {
        fun onClick(item: CurrencyModel)
    }
}