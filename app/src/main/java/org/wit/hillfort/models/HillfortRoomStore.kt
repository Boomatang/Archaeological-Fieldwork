package org.wit.hillfort.models

import android.content.Context
import androidx.room.Room
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfort.room.Database
import org.wit.hillfort.room.HillfortDao

class HillfortRoomStore(val context: Context) : HillfortStore, AnkoLogger {

    var dao: HillfortDao

    init {
        val database = Room.databaseBuilder(context, Database::class.java, "hillfort.db")
            .fallbackToDestructiveMigration()
            .build()
        dao = database.hillfortDao()
    }

    override fun create(hillfort: HillfortModel) {
        dao.create(hillfort)
    }

    override fun update(hillfort: HillfortModel) {
        dao.update(hillfort)
    }

    override fun delete(hillfort: HillfortModel) {
        dao.delete(hillfort)
    }

    override fun findAll(): List<HillfortModel> {
        return dao.findAll()
    }

    override fun findOne(id: Long): HillfortModel {
        return dao.findById(id)
    }

    override fun clear() {
        info { "Should clear the db" }
    }
}