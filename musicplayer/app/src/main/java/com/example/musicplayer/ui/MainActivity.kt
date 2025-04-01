package com.example.musicplayer.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.musicplayer.R
import com.example.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if nav_host_fragment exists before casting
        val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        if (fragment == null) {
            Log.e("MainActivity", "NavHostFragment is null! Check XML setup.")
            return
        }

        val navHostFragment = fragment as? NavHostFragment ?: run {
            Log.e("MainActivity", "nav_host_fragment is not a NavHostFragment")
            return
        }

        val navController = navHostFragment.navController

        // Determine screen width to toggle navigation views
        val isTablet = resources.configuration.smallestScreenWidthDp >= 600

        if (isTablet) {
            // Use NavigationRailView for tablets
            binding.navigationRailView.visibility = View.VISIBLE
            binding.bottomNavigationView.visibility = View.GONE
            binding.navigationRailView.setupWithNavController(navController)
        } else {
            // Use BottomNavigationView for phones
            binding.navigationRailView.visibility = View.GONE
            binding.bottomNavigationView.visibility = View.VISIBLE
            binding.bottomNavigationView.setupWithNavController(navController)
        }
    }
}
