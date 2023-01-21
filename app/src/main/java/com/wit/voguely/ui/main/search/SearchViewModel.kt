package com.wit.voguely.ui.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.ui.main.home.Product
import com.wit.voguely.ui.main.home.mockData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _itemSearched = MutableStateFlow<List<Product>>(listOf())
    val itemSearched : StateFlow <List<Product>> = _itemSearched.asStateFlow()

    private var _displayNoResultFound = MutableStateFlow(false)
    var displayNoResultFound : StateFlow<Boolean> = _displayNoResultFound



    fun searchItem(s: CharSequence){
        viewModelScope.launch {
             _itemSearched.update{ mockData.filter{it.itemName.contains(s)}}
          // val result : List<Product> = _itemSearched.update{ mockData.filter{it.itemName.contains(s)}}

               // _displayNoResultFound = true

        }

    }




}