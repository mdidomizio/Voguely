package com.wit.voguely.ui.login

import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wit.voguely.ui.MainActivity
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



    private fun login(email: String, password: String) =
        viewModelScope.launch (Dispatchers.Main) {
            try {
                val auth = Firebase.auth
                    .signInWithEmailAndPassword(email, password)
                    .await()
                _event.emit(LoginSuccessful())
            }catch(e: Exception){
                _event.emit(LoginError(e.localizedMessage))
            }
        }

    private fun signUp(email: String, password: String) =
        viewModelScope.launch (Dispatchers.Main){
            try {
                val auth = Firebase.auth
                    .createUserWithEmailAndPassword(email, password)
                    .await()
                _event.emit(LoginSuccessful())
            }catch (e: Exception){
                _event.emit(LoginError(e.localizedMessage))
            }

        }

   /* private suspend fun isLoggedIn () =
        viewModelScope.launch (Dispatchers.Main) {
            try{
                FirebaseAuth.getInstance().getCurrentUser()!= null
                    _event.emit(LoggedIn())

                /*splash_logo.alpha = 0f
                splash_logo.animate().setDuration(3000).alpha(1f).withEndAction {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    finish() */

            }catch(e: Exception){
                _event.emit(LoggedOut(e.localizedMessage))

              /*  splash_logo.alpha = 0f
                splash_logo.animate().setDuration(3000).alpha(1f).withEndAction {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    finish() */


            }
    } */



       /* private suspend fun isLoggedIn () =
       viewModelScope.launch(Dispatchers.Main){
            try{
                val auth = Firebase.auth
                val currentUser = auth.currentUser
                if(currentUser != null) {
                    _event.emit(LoggedIn())
                }
            }catch (e: Exception){
                _event.emit(LoggedIn(e.localizedMessage))
            }

        }*/





}