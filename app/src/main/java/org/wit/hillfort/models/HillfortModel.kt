package org.wit.hillfort.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity
data class HillfortModel(@PrimaryKey(autoGenerate = true)
                         var id: Long = 0,
                         var title: String = "",
                         var description: String = "",
                         var image: String = "",
                         var rating: Float = 0F,
                         var favourite: Boolean = false,
                         var lat: Double = 0.0,
                         var lng: Double = 0.0,
                         var zoom: Float = 0F) :Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0F): Parcelable