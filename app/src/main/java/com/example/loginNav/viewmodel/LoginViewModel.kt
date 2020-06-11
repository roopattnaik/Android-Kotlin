package com.example.loginNav.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*
View Model for LoginFragment
 */
class LoginViewModel : ViewModel() {
    private val loginfragtext = MutableLiveData<String>().apply {
        value = "This is Login Fragment"
    }
    val text: LiveData<String> = loginfragtext
    val loginResult = MutableLiveData<Boolean>()
    var emailAddress= ObservableField("")
    var password = ObservableField("")

    fun performLogin(emailAddress: String, password: String) = loginResult.postValue(checkCredentials(emailAddress, password))

    fun checkCredentials(emailAddress: String, password: String): Boolean  =  emailAddress== "admin" && password == "admin"


}

