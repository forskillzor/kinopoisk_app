package com.example.kinopoisk

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.kinopoisk.MainViewModel
import com.example.kinopoisk.data.Repository
import kotlinx.coroutines.launch

const val APPLICATION_TAG = "application_tag"

class MainActivity : AppCompatActivity() {
    val repository: Repository = Repository.newInstance()
    val viewModelFactory = MainViewModelFactory(repository)
    val viewModel: MainViewModel by viewModels { viewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(APPLICATION_TAG, "message")
        lifecycleScope.launch {
            val list = viewModel.topList()
            Log.d(APPLICATION_TAG,  "$list")
        }
    }
}