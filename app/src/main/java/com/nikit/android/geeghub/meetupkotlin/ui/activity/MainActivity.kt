package com.nikit.android.geeghub.meetupkotlin.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.nikit.android.geeghub.meetupkotlin.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
