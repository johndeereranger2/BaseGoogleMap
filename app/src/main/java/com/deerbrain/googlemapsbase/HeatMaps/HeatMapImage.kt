package com.deerbrain.googlemapsbase.HeatMaps

import android.graphics.Bitmap
import android.graphics.Color
import android.media.Image
import android.util.Log

private const val TAG = "HeatMapImage"

class HeatMapImage {


    fun createHeatMap(width: Int, height: Int, name: String): Bitmap {

        val conf = Bitmap.Config.ARGB_8888 // see other conf types
        val bmp = Bitmap.createBitmap(width, height, conf) // this creates a MUTABLE bitmap
        //val canvas = Canvas(bmp)


        for (row in 0..(height - 2) ) {
            for (column in 0 .. (width - 2)){
                val value = createWeightedValue(column + 1 , row + 1 , name )
                when (value) {
                    in 0..1  -> {
                        bmp.setPixel(column, row, Color.rgb(0, 255, 0))
                    }
                    in 1..2 -> {
                        bmp.setPixel(column, row, Color.rgb(25, 225, 0))
                    }
                    in 2..3 -> {
                        bmp.setPixel(column, row, Color.rgb(50, 203, 0))
                    }
                    in 3..4 -> {
                        bmp.setPixel(column, row, Color.rgb(75, 178, 0))
                    }
                    in 4..5 -> {
                        bmp.setPixel(column, row, Color.rgb(100, 152, 0))
                    }
                    in 5..6 -> {
                        bmp.setPixel(column, row, Color.rgb(126, 126, 0))
                    }
                    in 6..7 -> {
                        bmp.setPixel(column, row, Color.rgb(152, 101, 0))
                    }
                    in 7..8 -> {
                        bmp.setPixel(column, row, Color.rgb(178, 75, 0))
                    }
                    in 8 ..9->{
                        bmp.setPixel(column, row, Color.rgb(203, 50, 0))
                    }
                    else ->{
                        bmp.setPixel(column, row, Color.rgb(225, 25, 0))
                    }
                }

            }
        }

        return bmp

    }


    fun createWeightedValue(X: Int, Y: Int, mapName: String): Double {

        //  var weightedValue = 0.0
        //    weightedValue = Y/10.0



        val maxDistance = MarkerLocationRealmManager.maxDistance(mapName)
        val southWestMarker = MarkerLocationRealmManager.southWestMarker(X, Y, mapName)
        val southEastMarker = MarkerLocationRealmManager.southEastMarker(X, Y, mapName)
        val northWestMarker = MarkerLocationRealmManager.northWestMarker(X, Y, mapName)
        val northEastMarker = MarkerLocationRealmManager.northEastMarker(X, Y, mapName)

//        val southeastValue = southEastMarker.distanceNearMarker
//        val southwestValue = southWestMarker.distanceNearMarker
//        val northeastValue = northEastMarker.distanceNearMarker
//        val northwestValue = northWestMarker?.theMarkerLocation.distanceNearMarker
        val weightedValue = ((southEastMarker + southWestMarker + northEastMarker + northWestMarker)/4.0)/((maxDistance*0.9)/10.0)
        //val weightedValue = 2.3//((southeastValue! + southwestValue! + northeastValue! + northwestValue!)/4.0 )/((maxDistance!*0.9)/10.0)
        Log.d(TAG, "createWeightedValue: $weightedValue")

        return weightedValue
    }

}