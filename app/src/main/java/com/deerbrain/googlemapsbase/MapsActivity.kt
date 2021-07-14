package com.deerbrain.googlemapsbase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.deerbrain.googlemapsbase.HeatMaps.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.realm.Realm
import java.util.*
import kotlin.collections.ArrayList

enum class ActualSystemState { initial, mapTiles, parcelTiles, parcelData }

private const val TAG = "MapsActivity"

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener{

companion object {
    lateinit var context: Context
}

    lateinit var mMap: GoogleMap
    var heatMapShown: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = applicationContext
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sydney = LatLng(34.0, -90.0)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(34.0, - 90.0),17f))
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.setOnMapClickListener(this)
    }

    override fun onMapClick(coordinate: LatLng?) {
        Log.i("MapClick", "$coordinate")
    }

    fun runBackgroundPressed(view: View){
        Log.d(TAG, "runBackgroundPressed: ")
        Toast.makeText(this,"Pressed",Toast.LENGTH_LONG).show()

       //TODO: make this be able to run on a background thread
        Log.d(TAG, "runBackgroundPressed: start time ${Date()}")

        val blocker = ActivityBlocker(this)
        blocker.showWithText("Loading")
        HeatMapRealmManager.createArrayOfMarkers(true)
        calcNearestRoad()
        blocker.remove()




    }


    fun calcNearestRoad() {
        Log.d(TAG, "calcNear: start time ${Date()}")
        val uid= "temp"
        val allMarkers = HeatMapRealmManager.getHeatMapMarkersForUID(uid)
        val roadMarkers = MarkerLocationRealmManager.getRoadMarkers(uid)

        var emptyDoubles = ArrayList<Double>()
        val cache = RoadMarkerCache()
        val accessCache = RoadMarkerCache()

        accessCache.loadmarkers(roadMarkers)
        cache.loadTreeStand()

        for (aMarkers in allMarkers) {
            val latOfMarker = aMarkers.theLat
            val longOfMarker = aMarkers.theLong
            emptyDoubles = ArrayList<Double>()
            emptyDoubles.add(89000.0)
            var closestMarker = emptyDoubles.min()

            for (cached in accessCache.markers) {
                val d = Utilities.haversineDistance(
                    latOfMarker,
                    longOfMarker,
                    cached.latitude,
                    cached.longitude
                )
                if (d < closestMarker!!) {
                    emptyDoubles.add(d)
                }
            }



            for (stand in cache.markers) {
                val latRoad = stand.latitude
                val longRoad = stand.longitude
                val d = Utilities.haversineDistance(latOfMarker, longOfMarker, latRoad, longRoad)
                if (d < closestMarker!!) {
                    emptyDoubles.add(d)
                }
            }

            closestMarker = emptyDoubles.min()
            var returnValue: Double = closestMarker!!
            if (closestMarker == null) {
                returnValue = 890000.0
            }
            //			//writeRealmClosestMarker(distance: closestMarker!, cID: aMarkers.counter) <worked
            MarkerLocationRealmManager.writeClosestMarker(aMarkers.id, returnValue)
        }
        Log.d(TAG, "calcNearestRoad: FINISHED time ${Date()}" )
    }




}