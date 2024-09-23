package com.example.coffeeordering

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.coffeeordering.model.StorageViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [OrderSummaryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderSummaryFragment : Fragment() {

    private val storageViewModel: StorageViewModel by activityViewModels()
    private lateinit var firstName:TextView
    private lateinit var lastName:TextView
    private lateinit var cityName:TextView
    private lateinit var addressName:TextView
    private lateinit var editAddressBtn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_order_summary, container, false)
        firstName=view.findViewById(R.id.firstNameTextView)
        lastName=view.findViewById(R.id.lastNameTextView)
        cityName=view.findViewById(R.id.cityTextView)
        addressName=view.findViewById(R.id.addressTextView)
        editAddressBtn=view.findViewById(R.id.editAddressBtn)


        editAddressBtn.setOnClickListener(){
            findNavController().navigate(R.id.action_orderSummaryFragment_to_billingFragment)
        }

        storageViewModel.checkoutDetails.observe(viewLifecycleOwner) { details ->
            firstName.text = details.firstName
            lastName.text=details.lastName
            cityName.text=details.city
            addressName.text=details.address
        }



        return view
    }


}