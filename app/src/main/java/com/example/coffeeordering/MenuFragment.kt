package com.example.coffeeordering

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MenuFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Hide the ActionBar when this fragment is displayed
        (activity as AppCompatActivity).supportActionBar?.hide()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.menu_onboard, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Show the ActionBar again when this fragment is destroyed or removed
        (activity as AppCompatActivity).supportActionBar?.show()
    }
}
