package org.wit.hillfort.views.maps

import android.app.Activity
import android.content.Intent
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.hillfort.helpers.LOCATION
import org.wit.hillfort.models.Location

class MapsPresenter(val view: MapsView) {

    var location = Location()

    init {
        location = view.intent.extras?.getParcelable<Location>(LOCATION)!!
    }

    fun initMap(map: GoogleMap) {
        val loc = LatLng(location.lat, location.lng)
        val options = MarkerOptions()
            .title("Hillfort")
            .snippet("GPS : $loc")
            .draggable(true)
            .position(loc)

        map.addMarker(options)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, location.zoom))
    }

    fun doUpdateLocation(lat: Double, lng: Double, zoom: Float) {
        location.lat = lat
        location.lng = lng
        location.zoom = zoom
    }

    fun doOnBackPressed() {
        val resultIntent = Intent()
        resultIntent.putExtra(LOCATION, location)
        view.setResult(Activity.RESULT_OK, resultIntent)
        view.finish()
    }

    fun doUpdateMarker(marker: Marker) {
        val loc = LatLng(location.lat, location.lng)
        marker.setSnippet("GPS : $loc")
    }
}