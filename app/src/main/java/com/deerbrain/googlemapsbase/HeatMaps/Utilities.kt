package com.deerbrain.googlemapsbase.HeatMaps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.deerbrain.googlemapsbase.App
import com.google.android.gms.maps.model.LatLng
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.*

private const val TAG = "Utilities"

class Utilities {
    companion object {
        fun printError(title: String){
            for (i in 0 ..20){
                Log.d(TAG, "printError: $title")
            }
        }

        fun imageWithImage(drawingId: Int, size: Int): Bitmap {
            val res = App.context.resources
            var bitmap = BitmapFactory.decodeResource(res, drawingId)
            var bitmapResized = Bitmap.createScaledBitmap(bitmap, size, size, true)

            return bitmapResized
        }

        fun metersToFeet(meters: Double): Double {
            val rounded = roundOffDecimal(meters * 3.280)
            if (rounded != null){
                return rounded
            } else {
                return (meters * 3.280)
            }
        }

        fun roundOffDecimal(number: Double): Double? {
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.CEILING
            return df.format(number).toDouble()
        }

        fun sqMetersToAcres(sqMeters: Double):Double {
            val rounded = roundOffDecimal(sqMeters * 0.000247105)
            if (rounded != null){
                return rounded
            } else {
                return (sqMeters * 0.000247105)
            }
        }

        fun latLngArray(latitudes: MutableList<Double>, longitudes: MutableList<Double>): MutableList<LatLng>{
            var returnArray = mutableListOf<LatLng>()
            for (i in 0 ..(latitudes.size -1)){
                val coordinate = LatLng(latitudes[i],longitudes[i])
                returnArray.add(coordinate)
            }
            return returnArray
        }

        fun truncateTo8Places(input: Double) : Double {
            val df = DecimalFormat("#.########")
            df.roundingMode = RoundingMode.CEILING
            val stringNumber = df.format(input)
            return stringNumber.toDouble()
        }

        fun haversineDistance(la1: Double, lo1: Double, la2: Double, lo2: Double, radius: Double = 6367.4447) : Double {
            var haversin: (Double) -> Double = { angle: Double -> (1 - cos(angle))/2 }
            var ahaversin: (Double) -> Double = { angle: Double -> 2*asin(sqrt(angle)) }
            // Converts from degrees to radians

            var dToR: (Double) -> Double = { angle: Double -> (angle / 360) * 2 * PI }
            var lat1 = dToR(la1)
            var lon1 = dToR(lo1)
            var lat2 = dToR(la2)
            var lon2 = dToR(lo2)


            return radius * ahaversin(haversin(lat2 - lat1) + cos(lat1) * cos(lat2) * haversin(lon2 - lon1)) * 1000
        }


        //Log.d(TAG, "COLOR IS: ${Color.parseColor("#CC5500")}")

//        fun toast(text: String) {
//            toast?.cancel()
//            toast = Toast.makeText(this, text, Toast.LENGTH_SHORT)
//            toast?.show()
//        }




    }
}