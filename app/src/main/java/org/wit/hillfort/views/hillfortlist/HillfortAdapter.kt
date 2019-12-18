package org.wit.hillfort.views.hillfortlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_hillfort.view.*
import org.wit.hillfort.R
import org.wit.hillfort.helpers.readImageFromPath
import org.wit.hillfort.models.HillfortModel

interface HillfortListener {
    fun onHillfortClick(hillfort: HillfortModel)
}

class HillfortAdapter constructor(
    private var hillforts: List<HillfortModel>,
    private var listener: HillfortListener
) : RecyclerView.Adapter<HillfortAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_hillfort,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val hillfort = hillforts[holder.adapterPosition]
        holder.bind(hillfort, listener)
    }

    override fun getItemCount(): Int {
        return hillforts.size
    }

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(hillfort: HillfortModel, listener: HillfortListener) {
            itemView.hillfortTitle.text = hillfort.title
            itemView.description.text = hillfort.description
            itemView.cardRatingBar.rating = hillfort.rating
            itemView.setOnClickListener {listener.onHillfortClick(hillfort)}

            if(hillfort.image.isNotEmpty()) {
                itemView.imageView.setImageBitmap(readImageFromPath(itemView.context, hillfort.image))
            } else {
                itemView.imageView.visibility = View.INVISIBLE
            }

            if(hillfort.favourite) {
                itemView.favStar.visibility = View.VISIBLE
            } else {
                itemView.favStar.visibility = View.INVISIBLE
            }
        }
    }
}

