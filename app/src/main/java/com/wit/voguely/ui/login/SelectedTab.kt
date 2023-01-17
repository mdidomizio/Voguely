package com.wit.voguely.ui.login

import com.wit.voguely.R

enum class SelectedTab (
    val welcomeMessage : Int,
    val buttonText: Int
        ){

    LOGIN(
        welcomeMessage = R.string.SubtitleTextLogin,
        buttonText = R.string.loginButton
    ),
    SIGN_UP(
        welcomeMessage = R.string.SubtitleTextSignUp,
        buttonText = R.string.signUpButton
    )
}