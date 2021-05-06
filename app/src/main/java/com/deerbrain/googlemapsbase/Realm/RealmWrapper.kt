package com.deerbrain.googlemapsbase.Realm

import com.deerbrain.googlemapsbase.App
import com.deerbrain.googlemapsbase.App.Companion.context
import com.deerbrain.googlemapsbase.MapsActivity
import io.realm.Realm


object RealmWrapper {
    private var iRealm: Realm? = null

    val realm: Realm
        get() {

            if (iRealm == null) {

                RealmManager.initalize(App.context)

                iRealm = RealmManager.getRealm("myRealm", 4) { schema, version ->

                    if (version == 4L) return@getRealm

//

                }
            }

            return iRealm!!
        }
}