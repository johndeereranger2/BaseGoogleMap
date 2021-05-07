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

//     fun northWestMarker(x: Int, y: Int, mapName: MapNames) : MarkerLocation? {

//      //   val map = RealmWrapper.realm.objects<MapNames>()
//         //return mapName.theMarkerLocation.filter("row == %@ x and col == %@" , y + 1, x).first
//     //return RealmWrapper.realm.where(MapNames::class.java).equalTo("row",x).findFirst()
        
//         return RealmWrapper.realm.where(MapNames::class.java)
//             .equalTo("theMarkerLocation.row",x)
//             .equalTo("theMarkerLocation.col", y +1 )
//             .findFirst()
//     }
    
    
    //https://stackoverflow.com/questions/49483520/how-to-use-linkingobject-for-android
       fun northWestMarker(x: Int, y: Int, mapName: MapNames) : Double {

           //check on this result
           return RealmWrapper.realm.where(MapNames::class.java)
            .equalTo("theMarkerLocation.row",x)
            .equalTo("theMarkerLocation.col", y +1 )
            .findFirst().getDouble("distanceNearMarker")
//            https://docs.mongodb.com/realm-legacy/docs/kotlin/latest/index.html
//            // All fields are accessed using strings
// val name = person.getString("name")
// val age = person.getInt("age")
           
           
           //another option based off
//                // Find a dog to update.
//     val dog = bgRealm.where<Dog>().equalTo("age", 1.toInt()).findFirst()!!
//     dog.age = 3 // Update its age value.
           
           val realmItem =  RealmWrapper.realm.where(MapNames::class.java)
            .equalTo("theMarkerLocation.row",x)
            .equalTo("theMarkerLocation.col", y +1 )
            .findFirst()!!
           return realmItem.distanceNearMarker
           
           
        var distanceToNearestMarker = 0.0
        return RealmWrapper.realm.where(MapNames::class.java)
            .equalTo("theMarkerLocation.row",x)
            .equalTo("theMarkerLocation.col", y +1 )
            .findFirst()
        
        val marker = RealmWrapper.realm.where(MapNames::class.java)
            .equalTo("theMarkerLocation.row",x)
            .equalTo("theMarkerLocation.col", y +1 )
            .findFirst()
        
        marker?.forEach{
            it.theMarkerLocation.forEach{
                distanceToNearestMarker = it.distanceNearMarker   
            }
        }
        return distanceToNearestMarker
        
    }

    fun maxDistance(mapName: MapNames) : Double? {
        return mapName.theMarkerLocation.max("distanceNearMarker")?.toDouble()
    }

    fun writeTheMarkerToRealm(latLng: LatLng, countID: Int, row: Int, col:Int){

    }
}
