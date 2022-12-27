package com.kudos.forageapp.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.kudos.forageapp.R
import com.kudos.forageapp.databinding.ActivityForageMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForageMainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var binding: ActivityForageMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForageMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavController()
        setupActionBar()
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        navController = navHostFragment.navController
    }

    private fun setupActionBar() {
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.forageListFragment,
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}