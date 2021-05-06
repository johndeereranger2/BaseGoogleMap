package com.deerbrain.googlemapsbase.Realm

import android.content.Context
import io.realm.*


object RealmManager {

    private var initialized = false

    fun initalize(context: Context) {

        if (!initialized) {

            initialized = true
            Realm.init(context)
        }
    }

    fun getRealm(name: String, version: Long, schema: (RealmSchema, Long) -> Unit) =
        Realm.getInstance(
            RealmConfiguration.Builder()
                .name(name)
                .schemaVersion(version)
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .migration { realm, oldVersion, _ -> schema(realm.schema, oldVersion) }
                .build())

    fun create(realm: Realm, model: RealmObject) {

        realm.executeTransaction { it.insert(model) }
    }

    inline fun<reified Model: RealmObject> read(realm: Realm, callback: (RealmResults<Model>) -> RealmResults<Model>): RealmResults<Model> {

        return callback(realm.where<Model>(Model::class.java).findAll())
    }

    inline fun<reified Model: RealmObject> update(realm: Realm, from: Model, crossinline to: (Model) -> Unit) {

        realm.executeTransaction { to(from) }
    }

    fun delete(realm: Realm, model: RealmObject) {

        realm.executeTransaction { model.deleteFromRealm() }
    }
}