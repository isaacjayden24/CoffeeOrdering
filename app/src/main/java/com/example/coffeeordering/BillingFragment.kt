package com.example.coffeeordering

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.coffeeordering.model.StorageViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [BillingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BillingFragment : Fragment() {
   // private val sharedViewModel:StorageViewModel by activityViewModels()
    private val sharedViewModel: StorageViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_billing,container,false)







        return view
    }










}

