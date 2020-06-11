package com.example.loginNav.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginNav.RealmApplication
import com.example.loginNav.manager.RealmManager
import com.example.loginNav.model.RealmUsers

/*
View Model for Realm Fragment to add User
 */

open class RealmViewModel(): ViewModel() {

    val readResult = MutableLiveData<List<RealmUsers>>()
    val writeSuccess = MutableLiveData<Boolean>()

    var realmManager=RealmManager()
    var userId = ObservableField("")
    var userName = ObservableField("")
    var userLoc = ObservableField("")

    fun onSave() =
        writeSuccess.postValue(insertupdateUser(userId.get()?: userId.toString(), userName.get()?:userName.toString(), userLoc.get()?:userLoc.toString() ))


    fun insertupdateUser(id: String, name: String, loc: String):Boolean {
        if (id.isNotEmpty() && name.isNotEmpty() && loc.isNotEmpty()) {
            val user = RealmUsers(id, name, loc)
            realmManager.add(user)
        }
        getUserRealm()
        return true

    }

    fun getUserRealm() {
        val allusers: List<RealmUsers> = realmManager.findAll()
        readResult.postValue(allusers)
    }
}
