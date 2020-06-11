package com.example.loginNav.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/*
Realm Class for Users
 */
@RealmClass
open class RealmUsers(
    @PrimaryKey
    var userId: String ?=null,
    var name: String ? = null,
    var location: String ? = null
): RealmObject()