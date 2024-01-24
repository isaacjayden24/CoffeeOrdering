package com.example.coffeeordering

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.coffeeordering.model.StorageViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // declare the shared ViewModel class
    private val sharedViewModel: StorageViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login2, container, false)
        val button = view.findViewById<Button>(R.id.login_button)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment3_to_menuFragment)
            //onSubmit()
        }
        val button1=view.findViewById<Button>(R.id.sign_up_button)
        button1.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment3_to_signupFragment2)
        }



        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
