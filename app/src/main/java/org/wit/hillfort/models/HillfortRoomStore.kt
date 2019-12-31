package org.wit.hillfort.models

import android.content.Context
import androidx.room.Room
import org.wit.hillfort.room.Database
import org.wit.hillfort.room.HillfortDao

class HillfortRoomStore(val context: Context) : HillfortStore {

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}