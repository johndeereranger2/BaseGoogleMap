package com.deerbrain.googlemapsbase.HeatMaps

import android.media.Image

class HeatMapImage {


    fun createHeatMap(size: CGSize, mapName: MapName): Image {

        val height = height of CGsize
        val width = width of cgSize
        //generate aimage that is the size of the "Size" passed in

        //the image will be X size hight and x size wide
//      this is the iOS code to get a bitmap of a certain size
//        let colorSpace       = CGColorSpaceCreateDeviceRGB()
//        let width            = Int(size.width)
//        let height           = Int(size.height)
//        let bytesPerPixel    = 4
//        let bitsPerComponent = 8
//        let bytesPerRow      = bytesPerPixel * Int(size.width)
//        let bitmapInfo       = RGBA32.bitmapInfo
//        var value            = 0.0
//
//        guard let context = CGContext(data: nil, width: width, height: height, bitsPerComponent: bitsPerComponent, bytesPerRow: bytesPerRow, space: colorSpace, bitmapInfo: bitmapInfo) else {
//            print("unable to create context")
//            return nil
//        }
//        //context.draw(inputCGImage, in: CGRect(x: 0, y: 0, width: width, height: height))
//
//        guard let buffer = context.data else {
//            print("unable to get context data")
//            return nil
//        }
//
//        let pixelBuffer = buffer.bindMemory(to: RGBA32.self, capacity: width * height)

        for (row in 0..height ) {
            for (column in 0 .. (width - 1)){


                val value = createWeightedValue(size, column + 1, row + 1, mapName)
                when (value) {
                    value < 1.0 -> SetPixelColorTo(RGBA32(red: 0, green: 255, blue: 0, alpha: 255)
                    value < 2.0 -> SetPixelColorTo(RGBA32(red: 25, green: 225, blue: 0, alpha: 255)
                    value < 3.0 -> SetPixelColorTo(RGBA32(red: 50, green: 203, blue: 0, alpha: 255)
                    value < 4.0 -> SetPixelColorTo(RGBA32(red: 75, green: 178, blue: 0, alpha: 255)
                    value < 5.0 -> SetPixelColorTo(RGBA32(red: 101, green: 152, blue: 0, alpha: 255)
                    value < 6.0 -> SetPixelColorTo(RGBA32(red: 126, green: 126, blue: 0, alpha: 255)
                    value < 7.0 -> SetPixelColorTo(RGBA32(red: 152, green: 101, blue: 0, alpha: 255)
                    value < 8.0 -> SetPixelColorTo(RGBA32(red: 178, green: 75, blue: 0, alpha: 255)
                    value < 9.0 -> SetPixelColorTo(RGBA32(red: 203, green: 50, blue: 0, alpha: 255)
                    else -> SetPixelColorTo(RGBA32(red: 225, green: 25, blue: 0, alpha: 255)
                }

            }
        }

    }


    fun createWeightedValue(size: CGSize, X: Int, Y: Int, mapName: MapName): Double {
        //this function is not needed to touch this is the correct calculation if you have an issue
        //contact me with the issue
        val mgr = MarkerLocationRealmManager()
        val maxDistance = mgr.maxDistance(mapName)
        val southwestValue = mgr.southWestMarker(X, Y, mapName)
        val southeastValue = mgr.southEastMarker(X, Y, mapName)
        val northwestValue = mgr.northWestMarker(X, Y, mapName)
        val northeastValue = mgr.northEastMarker(X, Y, mapName)

//        val southeastValue = southEastMarker.distanceNearMarker
//        val southwestValue = southWestMarker.distanceNearMarker
//        val northeastValue = northEastMarker.distanceNearMarker
//        val northwestValue = northWestMarker.distanceNearMarker


        val weightedValue =
            ((southeastValue + southwestValue + northeastValue + northwestValue) / 4.0) / ((maxDistance * 0.9) / 10.0)


        return weightedValue
    }
}