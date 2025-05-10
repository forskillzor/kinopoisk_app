package com.example.kinopoisk

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kinopoisk.data.Repository
import com.google.android.material.bottomnavigation.BottomNavigationView

const val APPLICATION_TAG = "application_tag"

class MainActivity : AppCompatActivity() {
    //    private val repository: Repository = Repository.newInstance()
//    val viewModelFactory = MainViewModelFactory(repository)
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_navigation)?.setupWithNavController(
            navController
        )
    }
}