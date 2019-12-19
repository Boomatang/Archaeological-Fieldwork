package org.wit.hillfort.views.hillfortlist

import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_hillfort_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.hillfort.R
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

    fun doOnOptionsItemSelected(item: MenuItem?) {
        when (item?.itemId) {
            R.id.item_add -> doAddHillfort()
            R.id.show_fav -> showFavourites()
            R.id.hide_fav -> displayAll()
        }
    }
    fun doFilterHillfortList(filter: Boolean){
        if (filter){
            setupHillfortsListView(getHillforts().filter { it.favourite })

        } else {
            setupHillfortsListView(getHillforts())
        }
    }

    private fun showFavourites() {
        changeStarIcon(true)
        doFilterHillfortList(true)
    }

    private fun displayAll() {
       changeStarIcon(false)
        doFilterHillfortList(false)
    }

    private fun changeStarIcon(color: Boolean) {
        val showFav = view.menu.findItem(R.id.show_fav)
        val hideFav = view.menu.findItem(R.id.hide_fav)
        if (color) {
            showFav.isVisible = false
            hideFav.isVisible = true
        } else {
            showFav.isVisible = true
            hideFav.isVisible = false
        }
    }

    private fun setupHillfortsListView(hillfort: List<HillfortModel>){
        val layoutManager = LinearLayoutManager(view)
        view.recyclerView.layoutManager = layoutManager
        view.recyclerView.adapter = HillfortAdapter(hillfort, view)
        view.recyclerView.adapter?.notifyDataSetChanged()
    }
}