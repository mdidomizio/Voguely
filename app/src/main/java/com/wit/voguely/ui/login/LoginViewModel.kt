package com.wit.voguely.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

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

    fun onActionButtonClicked(email: String, password: String) {
        viewModelScope.launch {
            when (selectedTab.value) {
                SelectedTab.LOGIN -> login(email, password)
                SelectedTab.SIGN_UP -> signUp(email, password)
            }
        }
    }

    fun login(email: String, password: String) =
        viewModelScope.launch (Dispatchers.Main) {
            try {
                val result = Firebase.auth
                    .signInWithEmailAndPassword(email, password)
                    .await()
                _event.emit(LoginSuccessful())
            }catch(e: Exception){
                _event.emit(LoginError(e.localizedMessage))
            }
        }

    fun signUp(email: String, password: String) =
        viewModelScope.launch (Dispatchers.Main){
            try {
                val result = Firebase.auth
                    .createUserWithEmailAndPassword(email, password)
                    .await()
                _event.emit(LoginSuccessful())
            }catch (e: Exception){
                _event.emit(LoginError(e.localizedMessage))
            }

        }
}