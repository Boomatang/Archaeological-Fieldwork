package org.wit.hillfort.helpers

import org.wit.hillfort.models.HillfortModel

fun updateHillfort(hillfort: HillfortModel, hillforts: List<HillfortModel>) {
    var foundHillfort: HillfortModel? = hillforts.find { it.id == hillfort.id }
    if (foundHillfort != null) {
        foundHillfort.title = hillfort.title
        foundHillfort.description = hillfort.description
        foundHillfort.image = hillfort.image
        foundHillfort.rating = hillfort.rating
        foundHillfort.lat = hillfort.lat
        foundHillfort.lng = hillfort.lng
        foundHillfort.zoom = hillfort.zoom
        foundHillfort.favourite = hillfort.favourite
    }
}

fun setNewHillfortData(existHillfortModel: HillfortModel, newHillfortModel: HillfortModel) {
    existHillfortModel.title = newHillfortModel.title
    existHillfortModel.description = newHillfortModel.description
    existHillfortModel.image = newHillfortModel.image
    existHillfortModel.rating = newHillfortModel.rating
    existHillfortModel.lat = newHillfortModel.lat
    existHillfortModel.lng = newHillfortModel.lng
    existHillfortModel.zoom = newHillfortModel.zoom
    existHillfortModel.favourite = newHillfortModel.favourite
}