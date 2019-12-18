package org.wit.hillfort.views.hillfortlist

import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.hillfort.helpers.BLANK_REQUEST
import org.wit.hillfort.helpers.EDIT
import org.wit.hillfort.main.MainApp
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.views.edithillfort.EditHillfortView

class HillfortListPresenter(val view: HillfortListView) {

    var app: MainApp

    init {
        app = view.application as MainApp
    }

    fun getHillforts() = app.hillforts.findAll()

    fun doAddHillfort() {
         view.startActivityForResult<EditHillfortView>(BLANK_REQUEST)
    }

    fun doEditHillfort(hillfort: HillfortModel) {
        view.startActivityForResult(view.intentFor<EditHillfortView>().putExtra(EDIT, hillfort), BLANK_REQUEST)
    }

}