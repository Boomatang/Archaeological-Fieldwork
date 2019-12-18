package org.wit.hillfort.views.hillfortlist

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_hillfort_list.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jetbrains.anko.startActivity
import org.wit.hillfort.R
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.views.maphillforts.MapHillfortsView

class HillfortListView : AppCompatActivity(), HillfortListener {

  lateinit var presenter: HillfortListPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillfort_list)
    setupToolbar()
    presenter = HillfortListPresenter(this)
    showHillfortListView()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_add -> presenter.doAddHillfort()
      R.id.view_map ->  startActivity<MapHillfortsView>()
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onHillfortClick(hillfort: HillfortModel) {
    presenter.doEditHillfort(hillfort)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    showHillfortListView()
    super.onActivityResult(requestCode, resultCode, data)

  }

  private fun showHillfortListView() {
    doAsync {
      val forts = presenter.getHillforts()
      uiThread {
        setupHillfortsListView(forts)
      }
    }
  }

  private fun setupHillfortsListView(hillfort: List<HillfortModel>){
    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager
    recyclerView.adapter = HillfortAdapter(hillfort, this)
    recyclerView.adapter?.notifyDataSetChanged()
  }

  private fun setupToolbar(){
    toolbar.title = title
    setSupportActionBar(toolbar)
  }
}
