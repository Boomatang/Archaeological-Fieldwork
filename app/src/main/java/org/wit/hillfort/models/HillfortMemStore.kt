package org.wit.hillfort.models

import org.wit.hillfort.helpers.updateHillfort

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class HillfortMemStore : HillfortStore {
    override fun findOne(id: Long): HillfortModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val hillforts = ArrayList<HillfortModel>()

    override fun findAll(): List<HillfortModel> {
        return hillforts
    }

    override fun create(hillfort: HillfortModel) {
        hillfort.id = getId()
        hillforts.add(hillfort)
    }

    override fun update(hillfort: HillfortModel) {
        updateHillfort(hillfort, hillforts)
    }

    override fun delete(hillfort: HillfortModel) {
        hillforts.remove(hillfort)
    }
}