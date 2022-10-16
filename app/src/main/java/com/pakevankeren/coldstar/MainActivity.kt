package com.pakevankeren.coldstar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.pakevankeren.coldstar.databinding.ActivityMainBinding
import com.pakevankeren.coldstar.view.NowPlayingActivity
import com.pakevankeren.coldstar.viewModel.NowPlayingViewModel
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}