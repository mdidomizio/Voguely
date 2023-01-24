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

    fun onActionButtonClicked(email: String, password: String){
        viewModelScope.launch{
            when(selectedTab.value){
                SelectedTab.LOGIN -> login(email, password)
                SelectedTab.SIGN_UP -> signUp(email, password)

            }
        }
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch{

        }
    }


}