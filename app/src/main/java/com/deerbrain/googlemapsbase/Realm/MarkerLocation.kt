package com.deerbrain.googlemapsbase.Realm

import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey



open class MarkerLocation(
    @PrimaryKey var id: Int = 0,
    var theLat: Double = 0.0,
    var theLong: Double = 0.0,
    var row: Int = 0,
    var col: Int = 0,
    var distanceNearMarker: Double = 0.0,
    var insidePoly: Boolean = false,
    @LinkingObjects("polys")
    var parentRealm: RealmResults<MapNames>? = null): RealmObject()