package com.example.loginNav.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginNav.RealmApp
import com.example.loginNav.manager.RealmManager
import com.example.loginNav.model.RealmUsers


open class RealmViewModel(): ViewModel() {

    init {
        var myApp: RealmApp =
            RealmApp()
    }

    val readResult = MutableLiveData<List<RealmUsers>>()
    val writeSuccess = MutableLiveData<Boolean>()

    var realmManager=RealmManager()
    var user_id = ObservableField("")
    var user_name = ObservableField("")
    var user_loc = ObservableField("")

    fun onSave() =
        writeSuccess.postValue(insertupdateUser(user_id.get()?: user_id.toString(), user_name.get()?:user_name.toString(), user_loc.get()?:user_loc.toString() ))


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
