package com.dixitpatel.mycoffeevenue.mymodule.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dixitpatel.mycoffeevenue.R
import com.dixitpatel.mycoffeevenue.databinding.RowItemAllBinding
import com.dixitpatel.mycoffeevenue.mymodule.domain.model.CountriesModelItem


class CountryListAdapter constructor(private val onClickListener: OnClickListener): RecyclerView.Adapter<CountryListAdapter.PlacesViewHolder>() {

  interface OnClickListener {
    fun onClickRecyclerItem(item: CountriesModelItem)
    fun onClickSetHome(item: CountriesModelItem)
    fun onClickCalCalculateDistance(item: CountriesModelItem)
  }

  private val items: MutableList<CountriesModelItem> = mutableListOf()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding =
      DataBindingUtil.inflate<RowItemAllBinding>(inflater, R.layout.row_item_all, parent, false)
    return PlacesViewHolder(binding).apply {

      binding.root.setOnClickListener {
        onClickListener.onClickRecyclerItem(items[layoutPosition])
      }

      binding.btnSetHome.setOnClickListener {
        onClickListener.onClickSetHome(items[layoutPosition])
      }

      binding.btnCalculateDistance.setOnClickListener {
        onClickListener.onClickCalCalculateDistance(items[layoutPosition])
      }
    }
  }

  fun setCountriesList(placesList: List<CountriesModelItem>) {
    val previousItemSize = items.size
    items.clear()
    items.addAll(placesList)
    notifyItemRangeChanged(previousItemSize, placesList.size)
  }

  override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
    holder.binding.apply {
      item = items[position]
      tvItemImage.setImageResource(if(items[position].isHomeLocation()) R.drawable.ic__my_home else R.drawable.ic_pin)
      executePendingBindings()
    }
  }

  override fun getItemCount() = items.size

  class PlacesViewHolder(val binding: RowItemAllBinding) : RecyclerView.ViewHolder(binding.root)
}
