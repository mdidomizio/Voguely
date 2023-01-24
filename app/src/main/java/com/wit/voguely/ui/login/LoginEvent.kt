package com.wit.voguely.ui.login

open class LoginEvent {}

class LoginSuccessful : LoginEvent(){}
class LoginError(val errorMessage: String?) :LoginEvent(){}
