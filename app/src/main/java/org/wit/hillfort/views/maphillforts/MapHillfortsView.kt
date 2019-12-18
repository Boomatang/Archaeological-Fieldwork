package org.wit.hillfort.views.maphillforts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.activity_map_hillforts.*
import org.wit.hillfort.R

class MapHillfortsView : AppCompatActivity(), GoogleMap.OnMarkerClickListener {

    lateinit var map: GoogleMap
    lateinit var presenter: MapHillfortsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_hillforts)
        setupToolbar()

        presenter = MapHillfortsPresenter(this)

        mapView2.onCreate(savedInstanceState)
        mapView2.getMapAsync{
            map = it
            presenter.mapInit()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mapView2.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView2.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        mapView2.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView2.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView2.onSaveInstanceState(outState)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        presenter.onMarkerClick(marker)
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            presenter.doOnOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setupToolbar() {
        toolbarmap.title = title
        setSupportActionBar(toolbarmap)
    }
}
