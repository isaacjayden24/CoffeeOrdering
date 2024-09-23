package com.example.coffeeordering

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.coffeeordering.data.CheckoutDetails
import com.example.coffeeordering.model.StorageViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [BillingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BillingFragment : Fragment() {
   // private val sharedViewModel:StorageViewModel by activityViewModels()
    private val sharedViewModel: StorageViewModel by activityViewModels()

    private lateinit var firstName:EditText
    private lateinit var lastName:EditText
    private lateinit var address:EditText
    private lateinit var city:EditText
    private lateinit var checkoutBtn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_billing,container,false)
        firstName=view.findViewById(R.id.first_name)
        lastName=view.findViewById(R.id.last_name)
        address=view.findViewById(R.id.address)
        city=view.findViewById(R.id.city)
        checkoutBtn=view.findViewById(R.id.checkout_button)


        checkoutBtn.setOnClickListener(){
            takeInputCheckoutDetails()
        }








        return view
    }


    //take input for CheckoutDetails details


    /*fun takeInputCheckoutDetails(){









            val firstInput = firstName.text.toString().trim()
            val lastInput = lastName.text.toString().trim()
            val addressInput = address.text.toString().trim()
            val cityInput = city.text.toString().trim()

            //create an object and pass it to the view model
            val checkoutDetails = CheckoutDetails(
                firstName = firstInput,
                lastName = lastInput,
                address = addressInput,
                city = cityInput
            )



            sharedViewModel.setCheckoutDetails(checkoutDetails)
            findNavController().navigate(R.id.action_billingFragment_to_orderSummaryFragment)



    }*/





fun takeInputCheckoutDetails() {
    // Retrieve the text inputs from the EditText fields
    val firstInput = firstName.text.toString().trim()
    val lastInput = lastName.text.toString().trim()
    val addressInput = address.text.toString().trim()
    val cityInput = city.text.toString().trim()

    // Validate each input field and display a Toast if any field is empty
    when {
        firstInput.isEmpty() -> {
            Toast.makeText(requireContext(), "First name is required", Toast.LENGTH_SHORT).show()
            return
        }
        lastInput.isEmpty() -> {
            Toast.makeText(requireContext(), "Last name is required", Toast.LENGTH_SHORT).show()
            return
        }
        addressInput.isEmpty() -> {
            Toast.makeText(requireContext(), "Address is required", Toast.LENGTH_SHORT).show()
            return
        }
        cityInput.isEmpty() -> {
            Toast.makeText(requireContext(), "City is required", Toast.LENGTH_SHORT).show()
            return
        }
    }

    // If all inputs are valid, create an object and pass it to the ViewModel
    val checkoutDetails = CheckoutDetails(
        firstName = firstInput,
        lastName = lastInput,
        address = addressInput,
        city = cityInput
    )

    // Pass the details to the shared ViewModel
    sharedViewModel.setCheckoutDetails(checkoutDetails)

    // Navigate to the next fragment
    findNavController().navigate(R.id.action_billingFragment_to_orderSummaryFragment)
}











}

