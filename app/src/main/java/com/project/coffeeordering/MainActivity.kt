package com.project.coffeeordering

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.play.integrity.internal.f
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.project.coffeeordering.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    // Declare FirebaseAuth instance
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase first
        try {
            if (FirebaseApp.getApps(this).isEmpty()) {
                FirebaseApp.initializeApp(this)
            }
        } catch (e: Exception) {
            Log.e("Firebase", "Error initializing Firebase: ${e.message}")
        }
        //initialize firebase
        auth = FirebaseAuth.getInstance()

        // Setup view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Navigation
        try {
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
              navController = navHostFragment.navController


            if (savedInstanceState != null) {
                // Restore the navigation state
                val navState = savedInstanceState.getParcelable<Bundle>("nav_state")
                navState?.let { navController.restoreState(it) }
            }

        } catch (e: Exception) {
            Log.e("Navigation", "Error setting up navigation: ${e.message}")
        }








    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the navigation state
        navController.saveState()?.let { outState.putParcelable("nav_state", it) }
    }


    override fun onStart() {
        super.onStart()

        // Only navigate if the activity is being launched for the first time
        if (intent?.action == null) {
            // Check if the user is signed in
            val currentUser = auth.currentUser
            if (currentUser != null) {
                // User is signed in, navigate to MenuFragment
                navigateToMenuFragment()
            } else {
                // User is not signed in, navigate to SignUpFragment
                navigateToSignUpFragment()
            }
            // Mark the intent as handled to prevent future navigations
            intent?.action = "NAV_HANDLED"
        }
    }



    private fun navigateToMenuFragment() {
        // Navigate to the menu
        val navController = findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.menuFragment)
    }

    private fun navigateToSignUpFragment() {
        // Navigate to the sign-up or login screen
        val navController = findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.signUpFragment)
    }


    //function to logout users after some period of time
   /* private fun startLogoutTimer() {
        //  Handler to delay actions for 10 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            // Log out the user after 10 seconds
            logOutAndNavigateToLogin()
        }, logoutDelayMillis)
    }

    private fun logOutAndNavigateToLogin() {
        // Sign out the current user
        auth.signOut()

        // Navigate to signup fragment
        val navController = findNavController(R.id.nav_host_fragment)
        navController.navigate(R.id.signUpFragment)

        //  show a message that the user has been logged out
        Toast.makeText(this, "You have been logged out", Toast.LENGTH_SHORT).show()
    }*/

}