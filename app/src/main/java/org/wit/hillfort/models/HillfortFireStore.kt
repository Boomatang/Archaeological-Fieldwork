package org.wit.hillfort.models

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.wit.hillfort.helpers.setNewHillfortData

class HillfortFireStore(val context: Context) : HillfortStore {

    val hillforts = ArrayList<HillfortModel>()
    lateinit var userId: String
    lateinit var db: DatabaseReference

    override fun findAll(): List<HillfortModel> {
        return hillforts
    }

    override fun findOne(id: Long): HillfortModel {
        val foundHillfort: HillfortModel? = hillforts.find { it.id == id }
        return foundHillfort!!
    }


    override fun create(hillfort: HillfortModel) {
        val key = db.child("users").child(userId).child("hillforts").push().key
        key?.let {
            hillfort.fbId = key
            hillforts.add(hillfort)
            db.child("users").child(userId).child("hillforts").setValue(hillfort)
        }
    }

    override fun update(hillfort: HillfortModel) {
        var foundHillfort: HillfortModel? = hillforts.find { it.fbId == hillfort.fbId }
        if (foundHillfort != null){
            setNewHillfortData(foundHillfort, hillfort)
        }

        db.child("users").child(userId).child("hillfortss").child(hillfort.fbId).setValue(hillfort)
    }

    override fun delete(hillfort: HillfortModel) {
        db.child("users").child(userId).child("hillfortss").child(hillfort.fbId).removeValue()
        hillforts.remove(hillfort)
    }


    override fun clear() {
        hillforts.clear()
    }

    fun fetchHillforts(hillforsReady: () -> Unit) {
        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.mapNotNullTo(hillforts) {
                    it.getValue<HillfortModel>(HillfortModel::class.java)
                }
                hillforsReady()
            }
        }
        userId = FirebaseAuth.getInstance().currentUser!!.uid
        db = FirebaseDatabase.getInstance().reference
        clear()
        db.child("users").child(userId).child("hillforts").addListenerForSingleValueEvent(valueEventListener)
    }
}