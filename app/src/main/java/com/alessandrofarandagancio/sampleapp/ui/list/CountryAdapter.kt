package com.alessandrofarandagancio.sampleapp.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alessandrofarandagancio.sampleapp.ui.utils.recycler_view.RecyclerViewDiffUtil
import com.alessandrofarandagancio.sampleapp.databinding.ItemCountryBinding
import com.alessandrofarandagancio.sampleapp.domain.model.Country

class CountryAdapter(var list: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateData(newList: List<Country>) {
        val utils = RecyclerViewDiffUtil(list, newList)
        list = newList
        DiffUtil.calculateDiff(utils).dispatchUpdatesTo(this)
    }

    class CountryViewHolder(private val itemBinding: ItemCountryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(country: Country) {
            itemBinding.head.text = "${country.name}, ${country.region}"
            itemBinding.description.text = country.capital
            itemBinding.right.text = country.code
        }
    }
}

