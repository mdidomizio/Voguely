package com.wit.voguely.ui.login

import com.wit.voguely.R

enum class SelectedTab(
    val welcomeMessage: Int,
    val buttonText: Int
) {
    LOGIN(
        welcomeMessage = R.string.subtitle_text_login,
        buttonText = R.string.loginButton
    ),
    SIGN_UP(
        welcomeMessage = R.string.subtitle_text_sign_up,
        buttonText = R.string.signUpButton
    )
}