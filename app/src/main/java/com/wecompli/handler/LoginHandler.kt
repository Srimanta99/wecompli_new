package com.wecompli.handler

interface LoginHandler {
    fun onLoginClick()
    fun checkblankValidation(): Boolean
    fun showHidePassword()
    fun rememberme()
    fun openForgotpassword()
    fun displayPopup()
}