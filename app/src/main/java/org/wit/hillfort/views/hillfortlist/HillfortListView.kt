package org.wit.hillfort.views.hillfortlist

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_hillfort_list.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfort.R
import org.wit.hillfort.models.HillfortModel

class HillfortListView : AppCompatActivity(), HillfortListener, AnkoLogger {

  lateinit var presenter: HillfortListPresenter
  lateinit var menu: Menu

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillfort_list)
    setupToolbar()
    presenter = HillfortListPresenter(this)
    showHillfortListView()
    setupListeners()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    this.menu = menu!!
    menuInflater.inflate(R.menu.menu_main, menu)
    val hideFav = menu.findItem(R.id.hide_fav)
    hideFav.isVisible = false
    return super.onCreateOptionsMenu(menu)
  }


  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    if (item != null) {
      presenter.doOnOptionsItemSelected(item)
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onHillfortClick(hillfort: HillfortModel) {
    presenter.doEditHillfort(hillfort)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    showHillfortListView(hasFilter())
    super.onActivityResult(requestCode, resultCode, data)

  }


  private fun showHillfortListView(filter: Boolean = false) {
    presenter.doFilterHillfortList(filter)
  }

  private fun setupToolbar(){
    toolbar.title = title
    setSupportActionBar(toolbar)
  }

  fun hasFilter() : Boolean{
    val hideFav = menu.findItem(R.id.hide_fav)
    return hideFav.isVisible
  }

  private fun setupListeners(){
    searchView.setOnQueryTextListener(SearchViewListener(this))

  }


}
