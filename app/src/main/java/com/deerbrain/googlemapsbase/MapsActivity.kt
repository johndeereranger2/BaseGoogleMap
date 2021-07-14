package com.deerbrain.googlemapsbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.deerbrain.googlemapsbase.HeatMaps.HeatMapImage

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.TileOverlay
import com.google.android.gms.maps.model.TileOverlayOptions
import java.net.URL

enum class ActualSystemState { initial, mapTiles, parcelTiles, parcelData }

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener{

    lateinit var mMap: GoogleMap
    var heatMapShown: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    fun handleShowHeatMap(){// set up a button that calls this
        if (heatMapShown){
            //hide HeatMap
        } else {
            //showHeatMap()
        }
    }

//    fun showHeatMap(){
//        var newImage = Image?
//        val maxLat = markers.max(ofProperty: "theLat") as Double?
//        val maxLong = marker.max(ofProperty: "thLong") as Double?
//        val minLat = marker.max(ofProperty: "theLat") as Double?
//        val minLong = marker.max(ofProperty: "thelong") as Double?
//
//        val countRow = marker.max(ofProperty: "row") as Int?
//        val countCol = marker.max(ofProperty: "col") as Int?
//
//        if (countRow > 2){
//            if (countCol > 2) {
//                val imageHeight = countRow - 1
//                val imageWidth  = countCol - 1
//
//                newImage = getImageFromURL(heatMapimageURL)
//
//                val imageSize = CGSize(width: imageWidth, height: imageHeight)
//
//                if (newImage == null){
//                    newImage = HeatMapImage().createWeightedValue(imageSize, mapName = selectedMap)
//
//                    storeImageAtURL(heatMapimageURL)
//                }
//
//            }
//        }
//
//        val southWestCorner = LatLng((minLat - 0.000463), (minLong - 0.000463))
//        val northEastCorner = LatLng((maxLat + 0.000463), (maxLat - 0.000463))
//
//        val overlayBounds= GMSCoordinateBounds(coordinate: southWestCorner, coordinate: northEastCorner)
//        val overlay = GMSOverlay(bounds: overlayBounds, icon: newImage)
//        overlay.opacity = 0.5
//        overlay.bearing = 0
//        mMap.addGroundOverlay(overlay)
//    }
//
//    val heatMapimageURL: URL?
//
//    fun setHeatMapImageURL(){
//        val imageName = "$UID" + ".png"
//        val imageURL = URL( temporarydirectory.appendingPathWithComponent(imageName))
//        heatMapimageURL = imageURL
//    }
//
//    fun createArrayOfMarkers(){
//        val longPts = Int(((abs(maxAreaLong - minAreaLong))/0.00092667).rounded(.awayFromZero))
//        val latPts = latPts = Int(((abs(maxAreaLat - minAreaLat))/0.00092667).rounded(.awayFromZero))
//        var countNumber = 0
//
//        for (longIncriment in 1 ...longPts) {
//            //realm.beginWrite()
//
//
//            for (latInc in 1 ... latPts) {
//                val adder = (Double(n -1) * 0.00092667)
//                val zAdder = baseLat - adder
//                countNumber = countNumber + 1
//                val location = latLng(z,y)
//                val inAreaorOut = GMSGemometryContainsLocation(z,y,polyPath,true) //iOS code
//
//
////            let newMarkerLocation = MarkerLocation.self()
////            newMarkerLocation.dateCreated = Date()
////            newMarkerLocation.counter = backIncrementID()
////            newMarkerLocation.row = n
////            newMarkerLocation.col = longinc
////            newMarkerLocation.markerType = 1
////            newMarkerLocation.UiD = UiD
////            newMarkerLocation.pictype = ""
////            newMarkerLocation.insidePoly = inOrOut
////
////
////            if let currentMap = self.selectedMap(in: realm) {
////            newMarkerLocation.thelat = Double(z)
////            newMarkerLocation.thelong = Double(y)
////            currentMap.theMarkerLocation.append(newMarkerLocation)
//        }
//            try! realm.commitWrite()
//        }
//        }
//    }


}