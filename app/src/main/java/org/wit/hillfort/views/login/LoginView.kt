package org.wit.hillfort.views.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.wit.hillfort.R

class LoginView :  AppCompatActivity(), AnkoLogger {
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpToolBar()
        presenter = LoginPresenter(this)
        setUpOnClickListeners()
        presenter.doCheckLoginState()
        presenter.hideProgress()
    }

    private fun setUpOnClickListeners() {
        signUpListener()
        logInListener()
    }

    private fun setUpToolBar(){
        toolbar.title = title
        setSupportActionBar(toolbar)
    }

    private fun signUpListener(){
        signUp.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()
            if (email == "" || password == "") {
                toast("Please provide email + password")
            }
            else {
                presenter.doSignUp(email,password)
            }
        }
    }

    private fun logInListener() {
        logIn.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()
            if (email == "" || password == "") {
                toast("Please provide email + password")
            }
            else {
                presenter.doLogin(email,password)
            }
        }
    }
}