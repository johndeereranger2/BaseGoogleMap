package com.deerbrain.googlemapsbase.Realm

import com.deerbrain.googlemapsbase.App
import com.deerbrain.googlemapsbase.App.Companion.context
import com.deerbrain.googlemapsbase.MapsActivity
import io.realm.FieldAttribute
import io.realm.Realm

object RealmWrapper {
    private var iRealm: Realm? = null

    val realm: Realm

        get() {

            if (iRealm == null) {

                RealmManager.initalize(App.context)

                iRealm = RealmManager.getRealm("myRealm", 4) { schema, version ->

                    if (version == 4L) return@getRealm

                    val dish = schema.get("Dish") ?: schema.create("Dish")

                    dish.addField("id", Int::class.java, FieldAttribute.PRIMARY_KEY)
                        .addField("name", String::class.java, FieldAttribute.REQUIRED)
                        .addField("price", Float::class.java)
                }
            }

            return iRealm!!
        }
}