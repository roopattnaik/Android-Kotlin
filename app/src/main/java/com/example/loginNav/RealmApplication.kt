package com.example.loginNav

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmApp : Application() {

    lateinit var realm: Realm

    override fun onCreate() {
        super.onCreate()

        initRealm()

    }

    fun initRealm(){
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("RealmUsers.realm")
            .schemaVersion(1)// Incremented during migration
            .build()

        Realm.setDefaultConfiguration(config)

    }

}