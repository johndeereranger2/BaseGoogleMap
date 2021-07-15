package com.deerbrain.googlemapsbase.HeatMaps

import android.util.Log
import com.deerbrain.googlemapsbase.MapsActivity

import com.deerbrain.googlemapsbase.Realm.MarkerLocation
import com.deerbrain.googlemapsbase.Realm.RealmManager
import com.deerbrain.googlemapsbase.Realm.RealmWrapper
import com.google.android.gms.maps.model.LatLng
import io.realm.Realm

import io.realm.RealmList
import io.realm.RealmResults
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.roundToInt

private const val TAG = "HeatMapRealmManager"

object HeatMapRealmManager {

    var minAreaLat = 0.0
    var minAreaLong = 0.0
    var maxAreaLat = 0.0
    var maxAreaLong = 0.0

    fun getHeatMapMarkersForUID(uid: String) : RealmResults<MarkerLocation> {
        return RealmWrapper.realm.where(MarkerLocation::class.java).equalTo("uid", uid).findAll()
    }

    fun removeMarkersFromUID(uid: String){
        val markers = getHeatMapMarkersForUID(uid)
        for (marker in markers){
            removeMarker(marker)
        }
    }


    fun removeMarker(marker: MarkerLocation) {
        RealmWrapper.realm.beginTransaction()
        marker.deleteFromRealm()
        RealmWrapper.realm.commitTransaction()
    }

    fun buildArrayList(input: RealmList<Double>):ArrayList<Double>{
        var returnArray = ArrayList<Double>()
        for (item in input){
            returnArray.add(item)
        }

        return returnArray
    }

    fun setAreaMinMax(){
        val mapSpreadIncrease = 0.007

            minAreaLong = -89.4
            minAreaLat = 34.000
            maxAreaLong = -90.00
            maxAreaLat = 34.0400



            minAreaLong = Utilities.truncateTo8Places(minAreaLong )
            minAreaLat = Utilities.truncateTo8Places(minAreaLat )
            maxAreaLong = Utilities.truncateTo8Places(maxAreaLong )
            maxAreaLat = Utilities.truncateTo8Places(maxAreaLat)
        Log.d(TAG, "setAreaMinMax: minlat $minAreaLat minLong $minAreaLong maxLat: $maxAreaLat maxLong: $maxAreaLong")
    }

    fun setSmallArea(){
        minAreaLong = -89.009
        minAreaLat = 34.000
        maxAreaLong = -90.00
        maxAreaLat = 34.001
    }

    fun incrementID(backgroundThreadRealm: Realm): Int {
        return (backgroundThreadRealm.where(MarkerLocation::class.java).max("id")?.toInt() ?: 0) + 1
    }

    suspend fun createArrayOfMarkers(isBig: Boolean){
        if(isBig) {
            setAreaMinMax()
        } else {
            setSmallArea()
        }

        Realm.init(MapsActivity.context)
        val config = RealmManager.getRealm("myRealm", 4) { schema, version ->
            if (version == 4L) return@getRealm
        }.configuration


        val backgroundThreadRealm : Realm = Realm.getInstance(config)


        val longPoints = ((abs(maxAreaLong - minAreaLong))/0.000926667).roundToInt()
        val latPoints = ((abs(maxAreaLat - minAreaLat))/0.000926667).roundToInt()
        var propUID = DBConstants.getPropertyUID()

        //RealmWrapper.realm.beginTransaction()
        var baseID = incrementID(backgroundThreadRealm) + 1
        var arrayToAdd = ArrayList<MarkerLocation>()
        for (longInc in 1 .. longPoints){
            //Log.d(TAG, "createArrayOfMarkers: longInc: $longInc propUID: $propUID")
            val longSpacing = (longInc - 1) *0.000926667
            val y = Utilities.truncateTo8Places(minAreaLong + longSpacing)
            for (n in 1 ..latPoints){
                val latSpacing = (n - 1) * 0.000926667
                val z = Utilities.truncateTo8Places(minAreaLat + latSpacing)
                val point = LatLng(z,y)
                //val inOrOUt = PolyUtil.containsLocation(z,y,tempPath,true)
                val inOrOut = true
                //Log.d(TAG, "createArrayOfMarkers: create at: $z $y")
                //writeHeatMarkerToRealm(mapName,n,longInc,inOrOUt,point)
                val isdivis = baseID % 100 === 0

                if (isdivis){
                    Log.d(TAG, "createArrayOfMarkers: create at: id:$baseID $z $y")
                }
                baseID = baseID + 1
                var newMarkerLocation = MarkerLocation()
                newMarkerLocation.id = baseID
                newMarkerLocation.row = n
                newMarkerLocation.col = longInc
                newMarkerLocation.uid = propUID!!
                newMarkerLocation.insidePoly = inOrOut
                newMarkerLocation.theLat = point.latitude
                newMarkerLocation.theLong = point.longitude
                arrayToAdd.add(newMarkerLocation)
                //mapNameModel.theMarkerLocation.add(newMarkerLocation)
            }

        }
        Log.d(TAG, "createArray: Finish 1st loop Time ${Date()}")
        for (item in arrayToAdd) {
            backgroundThreadRealm.executeTransaction {
                it.insert(item)
                //Log.d(TAG, "createArrayOfMarkers: ${item.id}")
  
            }
        }
        Log.d(TAG, "createArray: Finish writing Time ${Date()}")
    }


    fun writeHeatMarkerToRealm(mapName: String, row: Int, col: Int, inPoly: Boolean, point: LatLng){

        RealmWrapper.realm.beginTransaction()

        RealmWrapper.realm.commitTransaction()
        val newID = MarkerLocationRealmManager.incrementID()
        var newMarkerLocation = MarkerLocation()
        //newMarkerLocation.datecreated
        newMarkerLocation.id = newID
        newMarkerLocation.row = row
        newMarkerLocation.col = col
        newMarkerLocation.uid = DBConstants.getPropertyUID()
        newMarkerLocation.insidePoly = inPoly
        newMarkerLocation.theLat = point.latitude
        newMarkerLocation.theLong = point.longitude


        RealmWrapper.realm.executeTransaction{
            it.insert(newMarkerLocation)
        }
    }
}