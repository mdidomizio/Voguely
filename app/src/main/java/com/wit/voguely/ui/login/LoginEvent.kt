package com.wit.voguely.ui.login

sealed class LoginEvent {

    object LoginSuccessful : LoginEvent()
    class LoginError(val errorMessage: String? = "Your Login failed") : LoginEvent()

}
