package com.deerbrain.googlemapsbase.Realm

import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import java.util.*

open class MarkerLocation: RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var uid: String = ""
    var theLat: Double = 0.0
    var theLong: Double = 0.0
    var markerType: String = ""
    var yearSaved: Int = 2020
    var picType: String = ""
    var coord: String = ""
    var roadlat: Double = 0.0
    var roadlong: Double = 0.0
    var googleIndexPath: Int = 0
    var googlePlaceID: String = ""
    var distanceNearMarker: Double = 0.0
    var row: Int = 0
    var col: Int = 0
    var thisMarkerUID: String = UUID.randomUUID().toString()
    var insidePoly: Boolean = false
   //     @LinkingObjects("theMarkerLocation")
    //val parentRealm: RealmResults<MapNames>? = null

}


////
//class MarkerLocation: Object {
//    @objc dynamic var UiD: String = ""
//    @objc dynamic var thelat: Double = 0
//    @objc dynamic var thelong: Double = 0
//    @objc dynamic var markerType: Int = 0 // 1 = Array 2 = polyline
//    @objc dynamic var dateCreated: Date?
//    @objc dynamic var yearsaved: NSInteger = 2000
//    @objc dynamic var pictype: String = "" // this is the picture type that will show up with the user seclects this point.  There will need to be an image saved in the images.xcassets with that name for this to load correctly.
//    @objc dynamic var coord: String = ""
//    @objc dynamic var counter: Int = 0
//    @objc dynamic var roadlat: Double = 0
//    @objc dynamic var roadlong: Double = 0
//    @objc dynamic var googleIndexPath: Int = 0
//    @objc dynamic var googlePlaceID: String = ""
//    @objc dynamic var distanceNearMarker: Double = 0
//    @objc dynamic var row: Int = 0
//    @objc dynamic var col: Int = 0
//    @objc dynamic var polyUiD: String = ""
//    @objc dynamic var insidePoly: Bool = false
//    //@objc dynamic var insidePoly2: Bool = false
//    var parentCategory = LinkingObjects(fromType: MapNames.self, property: "theMarkerLocation")
//
//
//    override class func primaryKey() -> String? {
//        return "counter"
//    }
//}
//
//
