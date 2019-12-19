package org.wit.hillfort.helpers

import org.wit.hillfort.models.HillfortModel

fun updateHillfort(hillfort: HillfortModel, hillforts: List<HillfortModel>) {
    var foundHillfort: HillfortModel? = hillforts.find { it.id == hillfort.id }
    if (foundHillfort != null) {
        foundHillfort.title = hillfort.title
        foundHillfort.description = hillfort.description
        foundHillfort.image = hillfort.image
        foundHillfort.lat = hillfort.lat
        foundHillfort.lng = hillfort.lng
        foundHillfort.zoom = hillfort.zoom
    }
}