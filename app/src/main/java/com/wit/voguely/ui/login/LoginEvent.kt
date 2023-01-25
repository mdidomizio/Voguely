package com.wit.voguely.ui.login

open class LoginEvent {}

class LoginSuccessful : LoginEvent()
class LoginError(val errorMessage: String? = "Your Login failed") :LoginEvent()
class LoggedIn(val loggedInMessage: String? = "Your are not logged in yet") : LoginEvent()
class LoggedOut(val loggedOutMessage: String? = "Your are logged out now") : LoginEvent()
