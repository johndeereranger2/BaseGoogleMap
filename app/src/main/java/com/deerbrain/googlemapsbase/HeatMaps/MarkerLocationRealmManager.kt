package com.deerbrain.googlemapsbase.HeatMaps

import com.deerbrain.googlemapsbase.Realm.MapNames
import com.deerbrain.googlemapsbase.Realm.MarkerLocation
import com.deerbrain.googlemapsbase.Realm.RealmWrapper
import com.google.android.gms.maps.model.LatLng

class MarkerLocationRealmManager {
    
    //https://medium.com/@markosullivan/realm-multi-level-backlinks-inverse-relationships-with-linking-objects-836559ebec8

    var id = (RealmWrapper.realm.where(MarkerLocation::class.java).max("id")?.toInt() ?: 0) + 1

    fun southWestMarker(x: Int, y: Int, mapName: MapNames) : MarkerLocation{
        return mapName.theMarkerLocation.filter("row == %@ x and col == %@" , y, x).first
    }

    fun southEastMarker(x: Int, y: Int, mapName: MapNames) : MarkerLocation{
        return mapName.theMarkerLocation.filter("row == %@ x and col == %@" , y, x + 1).first
    }

    fun northEastMarker(x: Int, y: Int, mapName: MapNames) : MarkerLocation{
        return mapName.theMarkerLocation.filter("row == %@ x and col == %@" , y + 1, x + 1).first
    }

    fun northWestMarker(x: Int, y: Int, mapName: MapNames) : MarkerLocation? {

     //   val map = RealmWrapper.realm.objects<MapNames>()
        //return mapName.theMarkerLocation.filter("row == %@ x and col == %@" , y + 1, x).first
    //return RealmWrapper.realm.where(MapNames::class.java).equalTo("row",x).findFirst()
        
        return RealmWrapper.realm.where(MapNames::class.java)
            .equalTo("theMarkerLocation.row",x)
            .equalTo("theMarkerLocation.col", y +1 )
            .findFirst()
    }

    fun maxDistance(mapName: MapNames) : Double? {
        return mapName.theMarkerLocation.max("distanceNearMarker")?.toDouble()
    }

    fun writeTheMarkerToRealm(latLng: LatLng, countID: Int, row: Int, col:Int){

    }
}
