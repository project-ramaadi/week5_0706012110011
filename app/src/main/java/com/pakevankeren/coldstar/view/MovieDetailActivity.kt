package com.pakevankeren.coldstar.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pakevankeren.coldstar.R

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val theBar = supportActionBar
        theBar?.setDisplayHomeAsUpEnabled(true)
    }
}