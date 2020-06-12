package com.roopattnaik.loginNav.view.ui.activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.roopattnaik.loginNav.R

/*
Main Activity for the app
Custom Action barused
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.mytoolbar)
        setSupportActionBar(toolbar)
    }
}


