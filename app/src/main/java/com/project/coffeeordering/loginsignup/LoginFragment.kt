package com.project.coffeeordering.loginsignup


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.project.coffeeordering.R


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    //declare an instance of firebase auth
    private lateinit var auth: FirebaseAuth
    private lateinit var loginBtn: Button
    private lateinit var cancelbtn: Button
    private lateinit var usernameTextInput: EditText
    private lateinit var passwordTextInput: EditText


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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        loginBtn = view.findViewById(R.id.next_button)
        cancelbtn = view.findViewById(R.id.cancel_button)
        usernameTextInput = view.findViewById(R.id.usernameTextInput)
        passwordTextInput = view.findViewById(R.id.passwordTextInput)


        cancelbtn.setOnClickListener {
            goToStartPage()
        }
        loginBtn.setOnClickListener {login()}

        return view
    }

    private fun goToStartPage() {
        findNavController().navigate(R.id.action_loginFragment2_to_loginFragment)
    }


    private fun login() {
        val email = usernameTextInput.text.toString().trim()
        val password = passwordTextInput.text.toString().trim()



        // Add validation
        if (email.isEmpty() || password.isEmpty() ) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }




        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        findNavController().navigate(R.id.action_loginFragment2_to_menuFragment)
                    }
                }.addOnFailureListener { exception ->
                    Toast.makeText(
                        requireContext(),
                        exception.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

}