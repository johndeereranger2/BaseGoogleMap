package com.deerbrain.googlemapsbase.HeatMaps

import android.util.Log
import com.deerbrain.googlemapsbase.Realm.MarkerLocation
import com.deerbrain.googlemapsbase.Realm.RealmWrapper
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import io.realm.RealmResults

private const val TAG = "MarkerLocationManager"

object MarkerLocationRealmManager {

    fun incrementID() : Int {
        return (RealmWrapper.realm.where(MarkerLocation::class.java).max("id")?.toInt() ?: 0) + 1
    }

    fun writeClosestMarker(id: Int, distance: Double){
        val marker = RealmWrapper.realm.where(MarkerLocation::class.java).equalTo("id", id).findFirst()
        RealmWrapper.realm.executeTransaction {
            marker?.distanceNearMarker = distance
        }
    }

    fun getRoadMarkers(uid: String): RealmResults<MarkerLocation>{
        val mapMarkers = RealmWrapper.realm.where(MarkerLocation::class.java)
            .equalTo("uid", uid)
            .equalTo("markerType","surfaceRoad").findAll()
        return mapMarkers
    }

    fun getAccessMarkers(uid: String): RealmResults<MarkerLocation>{
        val mapMarkers = RealmWrapper.realm.where(MarkerLocation::class.java)
            .equalTo("uid", uid)
            .equalTo("markerType","accessRoad").findAll()
        return mapMarkers
    }



    fun markersForMap(name: String): RealmResults<MarkerLocation> {
        val uid = MapNamesRealmManager.getPropertyUID(name)
        return RealmWrapper.realm.where(MarkerLocation::class.java).equalTo("uid", uid).findAll()
    }

    fun rowCountOfMap(name:String): Int{
        val allMarkers = markersForMap(name)
        val maxRow = allMarkers.max("row")?.toInt() ?: 0 as Int
        return maxRow
    }

    fun colCountOfMap(name:String): Int{
        val allMarkers = markersForMap(name)
        val maxRow = allMarkers.max("col")?.toInt() ?: 0 as Int
        return maxRow
    }

    fun heatMapBounds(mapName: String) : LatLngBounds {
        var southwest = MarkerLocationRealmManager.southWestCorner(mapName)
        var northeast = MarkerLocationRealmManager.northEastCorner(mapName)
        var theBounds = LatLngBounds(southwest, northeast)
        return theBounds
    }


    fun northEastCorner(mapName: String): LatLng? {
        val allMarkers = markersForMap(mapName)
        val maxLat = allMarkers.max("theLat")?.toDouble() ?: 0.0
        val maxLong = allMarkers.max("theLong")?.toDouble() ?: 0.0
        Log.d(TAG, "northEastCorner: minmax maxLat: $maxLat maxLong $maxLong" )
        val location = LatLng(maxLat + 0.000463,maxLong + 0.000463)
        //location = LatLng(34.9628,-81.7990)
        return location
    }

    fun southWestCorner(mapName: String) : LatLng? {
        val allMarkers = markersForMap(mapName)
        val minLat = allMarkers.min("theLat")?.toDouble() ?: 0.0
        val minLong = allMarkers.min("theLong")?.toDouble() ?: 0.0
        Log.d(TAG, "southWestCorner: minmax minLat: $minLat , minlong $minLong")
        val location = LatLng(minLat - 0.000463,minLong - 0.0000463)
        //location = LatLng(34.9577,-81.8048)
        return location
    }
    fun markersWith(uid: String): RealmResults<MarkerLocation> {
        return RealmWrapper.realm.where(MarkerLocation::class.java).equalTo("polyUiD", uid)
            .findAll()
    }

    fun deleteMarkersWith(uid: String) {
        val deleteItem = markersWith(uid)
        removeMarkerLocation(deleteItem)

    }

    fun removeMarkerLocation(location: RealmResults<MarkerLocation>) {
        RealmWrapper.realm.executeTransaction {
            location.deleteAllFromRealm()
        }
    }

    fun southWestMarker(x: Int, y: Int, mapName: String) : Double{
        var distanceToNearestMarker = 0.0

        val propUID = MapNamesRealmManager.getPropertyUID(mapName)
        val dist = RealmWrapper.realm.where(MarkerLocation::class.java)
            .equalTo("uid", propUID)
            .equalTo("row",y)
            .equalTo("col",x).findFirst()!!
        distanceToNearestMarker = dist.distanceNearMarker


        return distanceToNearestMarker
    }

    fun southEastMarker(x: Int, y: Int, mapName: String) : Double{
        //return mapName.theMarkerLocation.filter("row == %@ x and col == %@" , y, x + 1).first
        var distanceToNearestMarker = 0.0
        val propUID = MapNamesRealmManager.getPropertyUID(mapName)
        val dist = RealmWrapper.realm.where(MarkerLocation::class.java)
            .equalTo("uid", propUID)
            .equalTo("row",y)
            .equalTo("col",x).findFirst()!!
        distanceToNearestMarker = dist.distanceNearMarker


        return distanceToNearestMarker
    }

    fun northEastMarker(x: Int, y: Int, mapName: String) : Double{
        var distanceToNearestMarker = 0.0
        val propUID = MapNamesRealmManager.getPropertyUID(mapName)
        val dist = RealmWrapper.realm.where(MarkerLocation::class.java)
            .equalTo("uid", propUID)
            .equalTo("row",y+1)
            .equalTo("col",x+1).findFirst()!!

        distanceToNearestMarker = dist.distanceNearMarker


        return distanceToNearestMarker
    }

    //https://stackoverflow.com/questions/49483520/how-to-use-linkingobject-for-android
    fun northWestMarker(x: Int, y: Int, mapName: String) : Double {

        var distanceToNearestMarker = 0.0
        val propUID = MapNamesRealmManager.getPropertyUID(mapName)
        val dist = RealmWrapper.realm.where(MarkerLocation::class.java)
            .equalTo("uid", propUID)
            .equalTo("row",y+1)
            .equalTo("col",x).findFirst()!!

        distanceToNearestMarker = dist.distanceNearMarker

        return distanceToNearestMarker
    }

    fun maxDistance(mapName: String) : Double {
        //return mapName.theMarkerLocation.max("distanceNearMarker")?.toDouble()!!
        val propUID = MapNamesRealmManager.getPropertyUID(mapName)
        val items = RealmWrapper.realm.where(MarkerLocation::class.java)
            .equalTo("uid",propUID)
        val max = items.max("distanceNearMarker")?.toDouble()!!
        return max
    }

}