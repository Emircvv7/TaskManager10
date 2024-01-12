package com.example.taskmanager

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val pref: Pref by lazy {
        Pref(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        if (!pref.isBoardingShowed())
        navController.navigate(R.id.onBoardingFragment)

       if (FirebaseAuth.getInstance().currentUser == null) {
           navController.navigate(R.id.phoneFragment)
       }


        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.profileFragment,
                R.id.taskFragment
            )
        )
        val fragmentWithoutNavView = setOf(R.id.onBoardingFragment, R.id.phoneFragment, R.id.verifyFragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (fragmentWithoutNavView.contains(destination.id)){
                navView.isVisible = false
                supportActionBar?.hide()
            }else{
                navView.isVisible = true
                supportActionBar?.show()
            }
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}