package com.project.coffeeordering.loginsignup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.project.coffeeordering.R


/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class SignUpFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailSignup: EditText
    private lateinit var passwordSignup: EditText
    private lateinit var confirmPasswordSignup: EditText
    private lateinit var signupbtn: Button
    private lateinit var textviewLogin: TextView
    private lateinit var cancelBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            auth = FirebaseAuth.getInstance()
        } catch (e: Exception) {
            Log.e("Firebase", "Error getting FirebaseAuth instance: ${e.message}")
            // display error message
            Toast.makeText(
                requireContext(),
                "Error initializing authentication. Please try again.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        // Initialize views
        emailSignup = view.findViewById(R.id.emailSignup)
        passwordSignup = view.findViewById(R.id.passwordSignup)
        confirmPasswordSignup = view.findViewById(R.id.confirmPasswordSignup)
        signupbtn = view.findViewById(R.id.sign_up_button)
        textviewLogin = view.findViewById(R.id.textViewLogin)
        cancelBtn=view.findViewById(R.id.cancel_button)

        // Set click listeners
        signupbtn.setOnClickListener { register() }
        textviewLogin.setOnClickListener { goToLogin() }
        cancelBtn.setOnClickListener{goToOnboarding()}

        return view
    }

    private fun register() {
        val email = emailSignup.text.toString().trim()
        val password = passwordSignup.text.toString().trim()
        val confirmPassword = confirmPasswordSignup.text.toString().trim()

        // Add validation
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(R.id.action_signUpFragment_to_menuFragment)
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), exception.localizedMessage, Toast.LENGTH_LONG).show()
                }
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun goToLogin() {
        findNavController().navigate(R.id.action_signUpFragment_to_loginFragment2)
    }

    private fun goToOnboarding(){
        findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
    }
}

