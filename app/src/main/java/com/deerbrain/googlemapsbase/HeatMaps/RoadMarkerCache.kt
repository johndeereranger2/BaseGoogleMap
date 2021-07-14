package com.deerbrain.googlemapsbase.HeatMaps

import com.deerbrain.googlemapsbase.Realm.MarkerLocation
import com.google.android.gms.maps.model.LatLng
import io.realm.RealmResults

class RoadMarkerCache {
    var markers: List<LatLng> = emptyList()

            fun loadmarkers(input: RealmResults<MarkerLocation>){
                markers = input.mapNotNull { marker ->  LatLng(marker.roadlat,marker.roadlong) }

            }

    fun loadTreeStand(){
        var tempList = mutableListOf<LatLng>()
        val baseLat = 34.00
        val baseLong = -90.00
        for (lat in 0 ..30){
            for (longadd in 0 ..30){
                val newLat = baseLat + (lat * .0002)
                val newLong = baseLong + (longadd * .0002)
                tempList.add(LatLng(baseLat,baseLong))
            }
        }
        markers = tempList

    }
}