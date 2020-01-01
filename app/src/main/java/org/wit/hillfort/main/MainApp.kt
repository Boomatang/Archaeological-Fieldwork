package org.wit.hillfort.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfort.models.*
import org.wit.hillfort.models.HillfortFireStore

class MainApp : Application(), AnkoLogger {

  lateinit var hillforts: HillfortStore

  override fun onCreate() {
    super.onCreate()
    hillforts = HillfortRoomStore(applicationContext)
//    hillforts = HillfortFireStore(applicationContext)
    info("Hill Forts started")
  }
}