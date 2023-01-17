package com.wit.voguely

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _loginSelected = MutableStateFlow<Boolean>(true)
    val loginSelected : StateFlow<Boolean> = _loginSelected


    fun selectedTab(){

        _loginSelected.value = true


        }
    }

}