package org.wit.hillfort.views.edithillfort

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_hillfort.*
import kotlinx.android.synthetic.main.activity_hillfort.description
import kotlinx.android.synthetic.main.activity_hillfort.hillfortTitle
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.wit.hillfort.R
import org.wit.hillfort.helpers.readImageFromPath
import org.wit.hillfort.models.HillfortModel

class EditHillfortView : AppCompatActivity(), AnkoLogger {

    lateinit var presenter: EditHillfortPresenter
    var hillfort = HillfortModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hillfort)
        setUpToolBar()
        presenter = EditHillfortPresenter(this)
        setUpListeners()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            presenter.doActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_hillfort, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> presenter.doCancel()
            R.id.item_delete -> presenter.doDelete()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun btnAddAction(){
        if (hillfortTitle.text.isNotEmpty()) {
            presenter.doAddorSave(hillfortTitle.text.toString(), description.text.toString(), ratingBar.rating, favouriteToggle.isChecked)
        } else {
            toast("Please Enter a title")
        }
    }

    fun showHillfort(hillfort: HillfortModel) {
        hillfortTitle.setText(hillfort.title)
        description.setText(hillfort.description)

        favouriteToggle.isChecked = hillfort.favourite

        hillfortImage.setImageBitmap(readImageFromPath(this, hillfort.image))
        ratingBar.rating = hillfort.rating
        if (hillfort.image != null) {
            chooseImage.setText(R.string.change_hillfort_image)
        }
        btnAdd.setText(R.string.update_hillfort)
    }

    private fun setUpListeners(){
        btnAdd.setOnClickListener { btnAddAction() }
        hillfortLocation.setOnClickListener { presenter.doSetLocation() }
        chooseImage.setOnClickListener { presenter.doSelectImage() }
        favouriteToggle.setOnClickListener { presenter.doChangeFavouriteSetting() }
    }

    private fun setUpToolBar(){
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
    }
}

