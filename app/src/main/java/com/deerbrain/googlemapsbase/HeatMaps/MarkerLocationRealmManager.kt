package com.deerbrain.googlemapsbase.HeatMaps

class MarkerLocationRealmManager {


    fun southWestMarker(x: Int, y: Int, mapName: MapNames) : MarkerLocation{
        return mapName.theMarkerLocation.filter("row == %@ x and col == %@" , y, x).first
    }

    fun southEastMarker(x: Int, y: Int, mapName: MapNames) : MarkerLocation{
        return mapName.theMarkerLocation.filter("row == %@ x and col == %@" , y, x + 1).first
    }

    fun northEastMarker(x: Int, y: Int, mapName: MapNames) : MarkerLocation{
        return mapName.theMarkerLocation.filter("row == %@ x and col == %@" , y + 1, x + 1).first
    }

    fun northWestMarker(x: Int, y: Int, mapName: MapNames) : MarkerLocation{
        return mapName.theMarkerLocation.filter("row == %@ x and col == %@" , y + 1, x).first
    }

    fun maxDistance(mapName: MapName) : Double {
        return mapName.theMarkerLocation.max(ofProperty: "distancenearMarker") as Double?
    }
}