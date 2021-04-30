package com.deerbrain.googlemapsbase.Parcel

import android.util.Log
import com.deerbrain.googlemapsbase.App
import com.google.android.gms.maps.model.Tile
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class ParcelCacheManager {

    companion object {
        val context = App.context

        public fun deleteAllParcelCache(){
            //delete all files inside of the parcel Cache
        }

        public fun deleteParcelFilesOver30Days(){
            //this will delete ParcelCache for all files that are older than 30 days old.
        }

        public fun parcelDirectorySizeMB() : Int {
            // calculate the directory size of all of the parcel tiles stored on the device.
            return 0
        }

        private fun isThereSpaceToWrite() : Boolean {
            //update this function to check for space on the device to ensure that we have space to store the jpg
            return true
        }
        private fun store(x: Int, y: Int, z: Int): Tile {

            Log.i("TAG", "getTile: " )
            val folder = File("${context.cacheDir}/tiles");
            if (!folder.exists()) {
                folder.mkdir()
            }
            val file = File("${context.cacheDir}/tiles/${x}_${y}_${z}.tile")
            if (isThereSpaceToWrite()) {

                try {
                    val inputStream = getTileUrl(
                        x,
                        y,
                        z
                    )?.readBytes()
                    val fileOutputStream = FileOutputStream(file)

                    fileOutputStream.write(inputStream)

                    fileOutputStream.flush()
                    fileOutputStream.close()
                } catch (e: Exception) {
                    Log.d("TAG", "getTile: " + e.message)
                }
            }

            return Tile(x, y, file.readBytes())
        }

        private fun getTileUrl(x: Int, y: Int, z: Int): URL? {
            return URL("https://mt1.google.com/vt/lyrs=y&x=${x}&y=${y}&z=${z}")
        }

        fun getTile(x: Int, y: Int, z: Int): Tile {
            Log.i("TAG", "getTile: " )
            //Is the line Below the best way to store files on the device but the user can't access those files?
            val file = File("${context.cacheDir}/tiles/${x}_${y}_${z}.tile")
            return if (file.exists()) {
                Tile(x, y, file.readBytes())
            } else {
                store(
                    x,
                    y,
                    z
                )
            }
        }
    }
}