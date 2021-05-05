package com.deerbrain.googlemapsbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.deerbrain.googlemapsbase.HeatMaps.HeatMapImage
import com.deerbrain.googlemapsbase.MapCache.MapCacheTileProvider
import com.deerbrain.googlemapsbase.Parcel.ParcelCacheTileProvider

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

    var currentSystemState: ActualSystemState = ActualSystemState.initial


    var selectedMap: MapName
    var heatMapShown: Boolean = false
    var casheTileLayerShown: Boolean = false
    var parcelTileLayerShown: Boolean = false
    var mapCacheTileOverlay: TileOverlay? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(34.0, -90.0)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(34.0, - 90.0),17f))
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.setOnMapClickListener(this)
       // mapCacheTileOverlay = mMap.addTileOverlay(TileOverlayOptions().tileProvider(MapCacheTileProvider()))
    }

    override fun onMapClick(coordinate: LatLng?) {
        Log.i("MapClick", "onMapClick")

        if (currentSystemState == ActualSystemState.parcelData) {
            Log.i("Maps", "Parcel Data Tap")
            if (coordinate != null) {
                didTapParcelData(coordinate)
            }
        }
    }

    fun setSystemState(state: ActualSystemState) {

        currentSystemState = state
        when (state) {
            ActualSystemState.initial -> {
               // hideFrament(parcelTap)
            }
            ActualSystemState.mapTiles -> {
                //hideFragment(parcelTap)
                casheTileLayerShown = true
                parcelTileLayerShown = false
                mapCacheTileOverlay = mMap.addTileOverlay(TileOverlayOptions().tileProvider(
                        MapCacheTileProvider()
                ))
            }
            ActualSystemState.parcelTiles -> {
                //hideFragment(parcelTap)
                casheTileLayerShown = false
                parcelTileLayerShown = true
                mapCacheTileOverlay = mMap.addTileOverlay(TileOverlayOptions().tileProvider(
                    ParcelCacheTileProvider()
                ))
            }

            ActualSystemState.parcelData -> {
                //showFragment(parcelTap)
            }

        }
    }

    fun didTapParcelData(coordinate: LatLng){

        //Show an activity Blocker with spinning wheel and "loading Data"
        ReportAllDataManager.fetchParcelData(latLng: coordinate) {
            //thisis a completion block


                //Main Thread
                //hide activity blocker
                //let shape = 2Dshape from Parcel data
            //draw shape on map
            //Present Parcel Detail Fragment
        }


    }

    fun handleShowHeatMap(){
        if (heatMapShown){
            //hide HeatMap
        } else {
            showHeatMap()
        }
    }

    fun showHeatMap(){
        var newImage = Image?
        val maxLat = markers.max(ofProperty: "theLat") as Double?
        val maxLong = marker.max(ofProperty: "thLong") as Double?
        val minLat = marker.max(ofProperty: "theLat") as Double?
        val minLong = marker.max(ofProperty: "thelong") as Double?

        val countRow = marker.max(ofProperty: "row") as Int?
        val countCol = marker.max(ofProperty: "col") as Int?

        if (countRow > 2){
            if (countCol > 2) {
                val imageHeight = countRow - 1
                val imageWidth  = countCol - 1

                newImage = getImageFromURL(heatMapimageURL)

                val imageSize = CGSize(width: imageWidth, height: imageHeight)

                if (newImage == null){
                    newImage = HeatMapImage().createWeightedValue(imageSize, mapName = selectedMap)

                    storeImageAtURL(heatMapimageURL)
                }

            }
        }

        val southWestCorner = LatLng((minLat - 0.000463), (minLong - 0.000463))
        val northEastCorner = LatLng((maxLat + 0.000463), (maxLat - 0.000463))

        val overlayBounds= GMSCoordinateBounds(coordinate: southWestCorner, coordinate: northEastCorner)
        val overlay = GMSOverlay(bounds: overlayBounds, icon: newImage)
        overlay.opacity = 0.5
        overlay.bearing = 0
        mMap.addGroundOverlay(overlay)
    }

    val heatMapimageURL: URL?

    fun setHeatMapImageURL(){
        val imageName = "$UID" + ".png"
        val imageURL = URL( temporarydirectory.appendingPathWithComponent(imageName))
        heatMapimageURL = imageURL
    }

    fun createArrayOfMarkers(){
        val longPts = Int(((abs(maxAreaLong - minAreaLong))/0.00092667).rounded(.awayFromZero))
        val latPts = latPts = Int(((abs(maxAreaLat - minAreaLat))/0.00092667).rounded(.awayFromZero))
        var countNumber = 0

        for (longIncriment in 1 ...longPts) {
            //realm.beginWrite()


            for (latInc in 1 ... latPts) {
                val adder = (Double(n -1) * 0.00092667)
                val zAdder = baseLat - adder
                countNumber = countNumber + 1
                val location = latLng(z,y)
                val inAreaorOut = GMSGemometryContainsLocation(z,y,polyPath,true) //iOS code


//            let newMarkerLocation = MarkerLocation.self()
//            newMarkerLocation.dateCreated = Date()
//            newMarkerLocation.counter = backIncrementID()
//            newMarkerLocation.row = n
//            newMarkerLocation.col = longinc
//            newMarkerLocation.markerType = 1
//            newMarkerLocation.UiD = UiD
//            newMarkerLocation.pictype = ""
//            newMarkerLocation.insidePoly = inOrOut
//
//
//            if let currentMap = self.selectedMap(in: realm) {
//            newMarkerLocation.thelat = Double(z)
//            newMarkerLocation.thelong = Double(y)
//            currentMap.theMarkerLocation.append(newMarkerLocation)
        }
            try! realm.commitWrite()
        }
        }
    }


}