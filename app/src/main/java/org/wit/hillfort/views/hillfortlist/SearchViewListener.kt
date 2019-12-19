package org.wit.hillfort.views.hillfortlist

import android.widget.SearchView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class SearchViewListener(val view: HillfortListView) : SearchView.OnQueryTextListener,
    AnkoLogger {
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        info(query)
        if (query != null) {
            view.presenter.doFilterHillfortList(filter = view.hasFilter(), search = query)
        }
        return false
    }

}