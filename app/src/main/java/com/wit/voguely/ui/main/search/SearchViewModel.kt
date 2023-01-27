package com.wit.voguely.ui.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.voguely.remote.AddToCartDataSource
import com.wit.voguely.remote.ProductDataSource
import com.wit.voguely.remote.ProductsDataSource
import com.wit.voguely.ui.main.home.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val productsDataSource = ProductsDataSource()
    private val addToCartDataSource = AddToCartDataSource()

    private val _itemSearched = MutableStateFlow <List<Product>> (emptyList())
    val itemSearched: StateFlow<List<Product>> = _itemSearched.asStateFlow()

    private var _displayNoResultFound = MutableStateFlow(false)
    var displayNoResultFound: StateFlow<Boolean> = _displayNoResultFound

    fun searchItem(charSequence: CharSequence) {
        val searchTerm = charSequence.toString()
        viewModelScope.launch {
            // Setting teh no result display to be hidden by default
            _displayNoResultFound.value = false

            if (searchTerm.isBlank()) {
                // When the search is empty, show an empty list
                _itemSearched.update {
                    _itemSearched.value = emptyList()
                    return@launch
                }
            } else {
                // When a search exists
                _itemSearched.update {
                    // Get the results
                    val foundItems = productsDataSource.getProducts().filter{
                        it.name.lowercase().contains(searchTerm.lowercase())
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

    fun addToCart (product: Product){
        viewModelScope.launch (Dispatchers.IO) {
            _itemSearched.value.let{
                addToCartDataSource.addItemToCart(product.id)
            }
        }
    }
}



/*fun searchItem(searchQuery: CharSequence) {
        viewModelScope.launch {
            if(searchQuery.isBlank()){
                _displayNoResultFound.value = false
                _itemSearched.value = ListOf<Product>()
                return@launch
            }
            var searchResults = mockData.filter{
                it.name.contains(searchQuery, ignoreCase = true)
            }
            _itemSearched.update {searchResults}
             _displayNoResultFound.value = searchResults.isEmpty()

}} */




