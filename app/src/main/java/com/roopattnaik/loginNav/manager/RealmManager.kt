package com.roopattnaik.loginNav.manager


import io.realm.Realm
import io.realm.RealmObject

/*
RealmManager Class with add and store functions
 */

class RealmManager {

    /*
    Insert and update data
     */
    fun <T : RealmObject> add(model: T) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm1 ->
            realm1.insertOrUpdate(model)
        }
        realm.close()
    }

    /*
    Fetch the data
     */
    inline fun <reified T : RealmObject> findAll(): List<T> {
        val realm = Realm.getDefaultInstance()
        val list = realm.copyFromRealm(
            realm.where(T::class.java)
                .findAll())
        realm.close()
        return list
    }

    inline fun <reified T : RealmObject> clearAll() {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm1: Realm ->
            realm1.delete(T::class.java)
        }
        realm.close()
    }
}