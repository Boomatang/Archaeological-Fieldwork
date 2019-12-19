package org.wit.hillfort.views.hillfortlist

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_hillfort_list.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jetbrains.anko.startActivity
import org.wit.hillfort.R
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.views.maphillforts.MapHillfortsView

class HillfortListView : AppCompatActivity(), HillfortListener {

  lateinit var presenter: HillfortListPresenter
  lateinit var menu: Menu

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillfort_list)
    setupToolbar()
    presenter = HillfortListPresenter(this)
    showHillfortListView()
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
    val hideFav = menu.findItem(R.id.hide_fav)
    showHillfortListView(hideFav.isVisible)
    super.onActivityResult(requestCode, resultCode, data)

  }


  private fun showHillfortListView(filter: Boolean = false) {
    presenter.doFilterHillfortList(filter)
  }

  private fun setupToolbar(){
    toolbar.title = title
    setSupportActionBar(toolbar)
  }
}
