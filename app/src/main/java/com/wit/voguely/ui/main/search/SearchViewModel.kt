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
    val itemSearched: StateFlow<List<Product>> = _itemSearched.asStateFlow()

    private var _displayNoResultFound = MutableStateFlow(false)
    var displayNoResultFound: StateFlow<Boolean> = _displayNoResultFound

    fun searchItem(charSequence: CharSequence) {
        val searchTerm = charSequence.toString()
        viewModelScope.launch {
            // Setting teh no result display to be hidden by default
            _displayNoResultFound.value = false

            if (searchTerm == "") {
                // When the search is empty, show an empty list
                _itemSearched.update {
                    emptyList()
                }
            } else {
                // When a search exists
                _itemSearched.update {
                    // Get the results
                    val foundItems = mockData.filter { product ->
                        product.itemName.lowercase().contains(searchTerm.lowercase())
                    }

                    // When there are no results, show the no results label again
                    if (foundItems.isEmpty()) {
                        _displayNoResultFound.value = true
                    }

                    // Return the results. Works both with results, and when empty
                    foundItems
                }
            }
        }
    }
}




