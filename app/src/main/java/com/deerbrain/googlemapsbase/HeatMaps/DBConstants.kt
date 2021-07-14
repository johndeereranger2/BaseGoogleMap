package com.deerbrain.googlemapsbase.HeatMaps

object DBConstants {
        fun getPropertyUID():String{
            val returnZ = MapNamesRealmManager.getPropertyUID("uid")
            return returnZ
        }
}
