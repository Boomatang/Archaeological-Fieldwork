package org.wit.hillfort.views.login

import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.wit.hillfort.main.MainApp
import org.wit.hillfort.models.HillfortFireStore
import org.wit.hillfort.views.hillfortlist.HillfortListView

class LoginPresenter(val view:  LoginView): AnkoLogger {

    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    var fireStore: HillfortFireStore? = null
    var app: MainApp

    init {
        app = view.application as MainApp
        if (app.hillforts is HillfortFireStore) {
            fireStore = app.hillforts as HillfortFireStore
        }

    }
        fun doLogin(email: String, password: String) {
        info { "Login was pressed" }
        showProgress()
        loginUser(email, password)
    }

    fun doSignUp(email: String, password: String) {
        info { "Sign up was pressed" }
        showProgress()
        createNewUser(email, password)
    }

    fun doCheckLoginState() {
        var user: FirebaseUser? = auth.currentUser
        if (user != null) {
            loadFireBaseElseStart()
        }
    }

    fun showProgress() {

        view.progressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        view.progressBar.visibility = View.GONE
    }



    private fun createNewUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(view) { task ->
            if (task.isSuccessful) {
                loadFireBaseElseStart()
            } else {
                view.toast("Sign Up Failed: ${task.exception?.message}")
            }
            hideProgress()
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(view) { task ->
            if (task.isSuccessful) {
                loadFireBaseElseStart()
            } else {
                view.toast("Sign Up Failed: ${task.exception?.message}")
            }
            hideProgress()
        }
    }

    private fun changeActivity(){
        hideProgress()
        view.startActivity<HillfortListView>()
    }

    private fun loadFireBaseElseStart(){
        if (fireStore != null) {
            fireStore!!.fetchHillforts { changeActivity() }
        } else {
            changeActivity()
        }
    }
}
