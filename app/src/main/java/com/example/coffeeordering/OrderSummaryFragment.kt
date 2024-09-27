package com.example.coffeeordering

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.coffeeordering.model.StorageViewModel
import androidx.appcompat.widget.Toolbar



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
    private lateinit var orderTextView: TextView
    private lateinit var addressName:TextView
    private lateinit var quantityOrderTextView: TextView
    private lateinit var editAddressBtn:Button
    private lateinit var orderBtn:Button
    private lateinit var coffeeImageView: ImageView
    private lateinit var coffeeName:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_order_summary, container, false)

        // Set up toolbar
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        // Enable back button in the toolbar
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Handle the navigation icon click
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack() // Go back to the previous fragment
        }









        firstName=view.findViewById(R.id.firstNameTextView)
        lastName=view.findViewById(R.id.lastNameTextView)
        cityName=view.findViewById(R.id.cityTextView)
        addressName=view.findViewById(R.id.addressTextView)
        editAddressBtn=view.findViewById(R.id.editAddressBtn)
        orderBtn=view.findViewById(R.id.orderButton)
        orderTextView=view.findViewById(R.id.orderTextView)
        quantityOrderTextView=view.findViewById(R.id.quantityOrderTextView)
        coffeeImageView=view.findViewById(R.id.coffeeImageView)
        coffeeName=view.findViewById(R.id.coffeeName)


        editAddressBtn.setOnClickListener {
            findNavController().navigate(R.id.action_orderSummaryFragment_to_billingFragment)
        }
        orderBtn.setOnClickListener {
            findNavController().navigate(R.id.action_orderSummaryFragment_to_deliveryMapFragment)
        }

        storageViewModel.checkoutDetails.observe(viewLifecycleOwner) { details ->
            firstName.text = details.firstName
            lastName.text=details.lastName
            cityName.text=details.city
            addressName.text=details.address
        }

        storageViewModel.priceIncrement.observe(viewLifecycleOwner) { priceIncrement ->
            orderTextView.text=priceIncrement.toString()
        }
        storageViewModel.quantityAmount.observe(viewLifecycleOwner){quantityAmount->
           quantityOrderTextView.text=quantityAmount.toString()
        }

        //live data observation for the image

        storageViewModel.imageResId.observe(viewLifecycleOwner){imageResId ->

            coffeeImageView.setImageResource(imageResId)

        }

        //live data to observe  the coffee name

        storageViewModel.coffeeNameModel.observe(viewLifecycleOwner) { coffeeNameModel ->
            coffeeName.text = coffeeNameModel.toString()
        }


        return view
    }


}