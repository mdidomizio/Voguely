package com.wit.voguely.ui

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainViewModel : ViewModel() {
    fun signOut(){
        Firebase.auth.signOut()
    }
}