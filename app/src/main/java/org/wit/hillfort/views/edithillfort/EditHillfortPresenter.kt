package org.wit.hillfort.views.edithillfort

import android.content.Intent
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.uiThread
import kotlinx.android.synthetic.main.activity_hillfort.*
import org.wit.hillfort.views.maps.MapsView
import org.wit.hillfort.helpers.*
import org.wit.hillfort.main.MainApp
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.models.Location
import org.jetbrains.anko.info

class EditHillfortPresenter(val view: EditHillfortView): AnkoLogger {


    var hillfort = HillfortModel()
    var location = Location(52.245696, -7.139102, 15f)
    var app: MainApp
    var edit = false

    init {
        app = view.application as MainApp
        if (view.intent.hasExtra(EDIT)) {
            edit = true
            hillfort = view.intent.extras?.getParcelable<HillfortModel>(EDIT)!!
            info("APP: loaded hillfort: $hillfort")
            view.showHillfort(hillfort)
        }
    }

    fun doAddorSave(title: String, description: String, rating: Float, favourite: Boolean){
        hillfort.title = title
        hillfort.description = description
        hillfort.rating = rating
        hillfort.favourite = favourite
        doAsync {
            if (edit) {
                app.hillforts.update(hillfort)
            } else {
                app.hillforts.create(hillfort)
            }
            uiThread {
                view.finish()
            }
        }
    }

    fun doCancel(){
        view.finish()
    }

    fun doDelete(){
        app.hillforts.delete(hillfort)
        view.finish()
    }

    fun doSelectImage(){
        showImagePicker(view, IMAGE_REQUEST)
    }

    fun doSetLocation(){
        if (hillfort.zoom != 0F) {
            location.lat = hillfort.lat
            location.lng = hillfort.lng
            location.zoom = hillfort.zoom
        }
        view.startActivityForResult(view.intentFor<MapsView>().putExtra(LOCATION, location), LOCATION_REQUEST)

    }

    fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent){
        when (requestCode) {
            IMAGE_REQUEST -> imageResult(data)
            LOCATION_REQUEST -> locationResult(data)
        }
    }

    fun doChangeFavouriteSetting() {
        info("APP: ${view.favouriteToggle.isChecked}")
//        hillfort.favourite = view.favouriteToggle.isChecked
    }

    private fun imageResult(data: Intent) {
        hillfort.image = data.data.toString()
        view.showHillfort(hillfort)
    }

    private fun locationResult(data: Intent) {
        location = data.extras?.getParcelable<Location>(LOCATION)!!
        hillfort.lat = location.lat
        hillfort.lng = location.lng
        hillfort.zoom = location.zoom
    }
}