package com.deerbrain.googlemapsbase.Realm

import com.google.android.gms.maps.model.Marker
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*


//how to create parent/child in models
//https://stackoverflow.com/questions/41502476/realm-adding-children-to-a-parent-then-querying-results-on-parent


open class MapNames(
    @PrimaryKey var id: Int = 0,
    var name: String = "",
    var theLat: Double = 0.0,
    var theLong: Double = 0.0,
    var dateCreated: Date = Calendar.getInstance().time,
    var zoom: Float = 0.0F,
    var UID: String = UUID.randomUUID().toString(),
    var heatMapCreated: Int = 0,
    var roadsOn: Boolean = true,
    var accessRoads: Boolean = true,
    var treeStandsOn: Boolean = true,
    var othersStandsOn: Boolean = true,
    var theMarkerLocation: RealmList<MarkerLocation> = RealmList<MarkerLocation>()

    //var polys: RealmList<MapPolys> = RealmList<MapPolys>()
) : RealmObject()
