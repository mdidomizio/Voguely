package com.wit.voguely.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _selectedTab = MutableStateFlow(SelectedTab.LOGIN)
    val selectedTab : StateFlow<SelectedTab> = _selectedTab.asStateFlow()

    private val _event = MutableSharedFlow<LoginEvent>()
    val event = _event.asSharedFlow()



    fun onSelectedTab(selectedTab: SelectedTab){
        viewModelScope.launch {
            _selectedTab.update { selectedTab }
        }

    }

}