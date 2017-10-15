package com.nikit.android.geeghub.meetupkotlin.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

import com.nikit.android.geeghub.meetupkotlin.R

class CreateAccountActivity : AppCompatActivity() {

    var etFirstName: EditText? = null
    var etSecondName: EditText? = null
    var etEmail: EditText? = null
    var etPassword: EditText? = null
    var etRepeatPassword: EditText? = null
    var btSubmit: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        etFirstName = findViewById(R.id.account_first_name) as EditText
        etSecondName = findViewById(R.id.account_secon_name) as EditText
        etEmail = findViewById(R.id.account_email) as EditText
        etPassword = findViewById(R.id.account_password) as EditText
        etRepeatPassword = findViewById(R.id.account_repeat_password) as EditText
        btSubmit = findViewById(R.id.account_bt_submit) as Button

        btSubmit?.setOnClickListener({ validateForm() })

    }


    private fun validateForm(): Boolean {
        var result = true

        if (etFirstName!!.text.isEmpty()) {
            result = false
            etFirstName!!.error = "can`t be empty"
        }
        if (etSecondName!!.text.isEmpty()) {
            result = false
            etSecondName!!.error = "cant be empty"
        }
        if (etEmail!!.text.isEmpty()) {
            result = false
            etEmail!!.error = "cant be empty"
        }

        if (etPassword!!.text.isEmpty()) {
            result = false
            etPassword!!.error = "cant be empty"
        } else if (etPassword!!.text.length < 6) {
            result = false
            etPassword!!.error = "password is too short"
        }
        if (etPassword!!.text == etRepeatPassword!!.text) {
            result = false
            etPassword!!.error = "passwords not match"
            etPassword!!.error = "passwords not match"
        }
        return result
    }
}
