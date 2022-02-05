package com.augieafr.gamesapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.augieafr.gamesapp.databinding.ActivityMainBinding
import com.augieafr.gamesapp.ui.GamesViewModel
import com.augieafr.gamesapp.utils.gone
import com.augieafr.gamesapp.utils.visible

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel:GamesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.isLoading.observe(this){isLoading ->
            with(binding.pbMain){
                if (isLoading) this.visible() else this.gone()
            }
        }
    }

    private fun setupNavigation() {
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            if (destination.id == R.id.detailGameFragment) binding.navView.gone()
            else binding.navView.visible()
        }
    }
}