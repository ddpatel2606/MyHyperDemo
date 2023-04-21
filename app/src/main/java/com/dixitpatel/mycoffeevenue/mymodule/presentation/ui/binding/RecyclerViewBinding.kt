package com.dixitpatel.mycoffeevenue.mymodule.presentation.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.dixitpatel.mycoffeevenue.mymodule.domain.model.CountriesModelItem
import com.dixitpatel.mycoffeevenue.mymodule.presentation.ui.adapter.CountryListAdapter
import com.dixitpatel.mycoffeevenue.mymodule.presentation.viewutils.recyclerViewAnimate

object RecyclerViewBinding {

  @JvmStatic
  @BindingAdapter("adapter")
  fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    val snapHelper = LinearSnapHelper()
    snapHelper.attachToRecyclerView(view)
    view.adapter = adapter.apply {
      /**
       * Adapter is ready to restore State when it has more than 0 items. RecyclerView will
       * provide the state to the LayoutManager as soon as the Adapter has 1 or more items.
       */
      stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }
    view.recyclerViewAnimate()
  }


  @JvmStatic
  @BindingAdapter("adapterCountriesList")
  fun bindAdapterCountriesList(view: RecyclerView, placesList: List<CountriesModelItem>?) {
    placesList?.let{ itemList ->
      view.adapter?.let { adapter ->
        (adapter as CountryListAdapter).setCountriesList(itemList)
      }
    }
  }

}
