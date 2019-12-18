package org.wit.hillfort.views.maphillforts

import android.view.MenuItem
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_map_hillforts.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.wit.hillfort.R
import org.wit.hillfort.helpers.readImageFromPath
import org.wit.hillfort.main.MainApp
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.views.edithillfort.EditHillfortView
import org.wit.hillfort.views.hillfortlist.HillfortListView

class MapHillfortsPresenter(val view: MapHillfortsView): AnkoLogger {

    var app: MainApp
    var hillfort = HillfortModel()

    init {
        app = view.application as MainApp
    }

    fun mapInit() {
        configureMap()
        hideBlankData()

    }

    fun onMarkerClick(marker: Marker) {
        hillfort = app.hillforts.findOne(marker.tag as Long)
        view.currentTitle.text = hillfort.title
        view.currentDescription.text = hillfort.description
        if(hillfort.image.isNotEmpty()) {
            view.currentImage.setImageBitmap(readImageFromPath(view, hillfort.image))
        } else {
            view.currentImage.visibility = View.INVISIBLE
        }
        hideBlankData()
    }

    fun doOnOptionsItemSelected(item: MenuItem) {
        when (item.itemId) {
            R.id.item_add -> view.startActivity<EditHillfortView>()
            R.id.view_list -> view.startActivity<HillfortListView>()
        }
    }

    private fun configureMap() {
        view.map.setOnMarkerClickListener(view)
        view.map.uiSettings.setZoomControlsEnabled(true)
        app.hillforts.findAll().forEach {
            val loc = LatLng(it.lat, it.lng)
            val options = MarkerOptions().title(it.title).position(loc)
            view.map.addMarker(options).tag = it.id
            view.map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))
        }
    }

    private fun hideBlankData(){
        if (hillfort.title.isEmpty()) {
            view.currentTitle.visibility = View.INVISIBLE
        } else {
            view.currentTitle.visibility = View.VISIBLE
        }

        if (hillfort.description.isEmpty()) {
            view.currentDescription.visibility = View.INVISIBLE
        } else {
            view.currentDescription.visibility = View.VISIBLE
        }

        if (hillfort.image.isEmpty()) {
            view.currentImage.visibility = View.INVISIBLE
        } else {
            view.currentImage.visibility = View.VISIBLE
        }


    }
}